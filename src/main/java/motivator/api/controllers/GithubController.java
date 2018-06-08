package motivator.api.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import motivator.api.config.HibernateUtil;
import motivator.api.models.Group;
import motivator.api.models.User;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.CommitService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kohsuke.github.GHCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class GithubController {
    private static final String FULL_REPOSITORY = "laszlobalint/javascript";
    private static final String OWNER = "laszlobalint";
    private static final String REPOSITORY = "javascript";

    private class GitHub {
        Integer id;
        String commitShal;
        String commitMessage;
        Date commitDate;
        List<String> changes = new ArrayList<>();

        private String listChanges (ArrayList<String> changes) {
            String changeList = "";
            for (String change: changes) {
                changeList = changeList.concat('\"' + change + "\", ");
            }
            changeList = changeList.trim().replace(changeList.charAt(changeList.length()-2), ' ').trim();
            return changeList;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"id\": \"" + id +
                    "\", \"commitShal\": \"" + commitShal +
                    "\", \"commitMessage\": \"" + commitMessage +
                    "\", \"commitDate\": \"" + commitDate +
                    "\", \"changes\": \"" + changes +
                    "\"}";
        }
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/app/github", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getGithubInfo(@RequestHeader(value = "Authorization") String Authorization) {

        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        List<GitHub> list = new ArrayList<>();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        String ownerQuery = "SELECT repository.owner FROM repository " +
                    "left join groups on groups.id = repository.group_id " +
                    "where groups.name = :groupName";
        SQLQuery ownerSql = session.createSQLQuery(ownerQuery);
        ownerSql.setParameter("groupName", user.getActiveGroup());
        ArrayList<String> ownerList = new ArrayList<String>(ownerSql.list());
        String owner = "";
        for (String own : ownerList) {
            owner = own;
        }

        String repoQuery = "SELECT repository.repo_name FROM repository " +
                    "left join groups on groups.id = repository.group_id " +
                    "where groups.name = :groupName";
        SQLQuery repoSql = session.createSQLQuery(repoQuery);
        repoSql.setParameter("groupName", user.getActiveGroup());
        ArrayList<String> repoList = new ArrayList<String>(repoSql.list());
        String repoName = "";
        for (String rep : repoList) {
            repoName = rep;
        }

        org.kohsuke.github.GitHub github = null;
        try {
            github = org.kohsuke.github.GitHub.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int size = 1;
        final RepositoryId repo = new RepositoryId(owner, repoName);
        final CommitService service = new CommitService();
        int counter = 0;

        for (Collection<RepositoryCommit> commits : service.pageCommits(repo, size)) {
            for (RepositoryCommit commit : commits) {
                counter++;
                GitHub temp = new GitHub();
                temp.id = counter;
                String shal = commit.getSha().substring(0, 7);

                List<GHCommit.File> kohsuke = null;
                try {
                    kohsuke = github.getRepository(owner + "/" + repoName).getCommit(shal).getFiles();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (GHCommit.File file : kohsuke) {
                    temp.changes.add(file.getFileName());
                }

                temp.commitShal = shal;
                temp.commitMessage = commit.getCommit().getMessage();
                temp.commitDate = commit.getCommit().getAuthor().getDate();

                list.add(temp);
            }
        }
        session.close();
        return ResponseEntity.ok(list.toString());
    }
}
