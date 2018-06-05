package motivator.api.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class GithubController {
    private static final String FULL_REPOSITORY = "laszlobalint/javascript";
    private static final String OWNER = "laszlobalint";
    private static final String REPOSITORY = "javascript";

    private class GitHub {
        private String commitShal;          // COMMIT SHAL
        private String commitMessage;       // COMMIT MESSAGE
        private Date commitDate;          // COMMIT DATE
        private HashMap<String, Integer> changes;   // CHANGED FILENAME + CONTENT OF FILE

        @Override
        public String toString() {
            return "GitHub{" +
                    "commitShal='" + commitShal + '\'' +
                    ", commitMessage='" + commitMessage + '\'' +
                    ", commitDate=" + commitDate +
                    ", fileNames=" + changes +
                    '}';
        }
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/app/github", method = RequestMethod.GET)
    public ResponseEntity<List<GitHub>> getGithubInfo(@RequestHeader(value = "Authorization") String Authorization) throws IOException {

        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        List<GitHub> list = new ArrayList<>();

        final int size = 1;
        final RepositoryId repo = new RepositoryId(OWNER, REPOSITORY);
        final CommitService service = new CommitService();

        for (Collection<RepositoryCommit> commits : service.pageCommits(repo, size)) {
            for (RepositoryCommit commit : commits) {
                GitHub temp = new GitHub();
                String shal = commit.getSha().substring(0, 7);
                temp.commitShal = shal;
                temp.commitMessage = commit.getCommit().getMessage();
                temp.commitDate = commit.getCommit().getAuthor().getDate();
                int additions = 0;
                int deletions = 0;
                int totals = 0;
                try {
                    additions = commit.getStats().getAdditions();
                    deletions = commit.getStats().getDeletions();
                    totals = commit.getStats().getTotal();
                } catch (NullPointerException e) {
                    System.out.println("Null");
                }
                try {
                    temp.changes.put("Additions", additions);
                    temp.changes.put("Deletions", deletions);
                    temp.changes.put("Totals", totals);
                } catch (NullPointerException e) {
                    System.out.println("Null");
                }
                System.out.println(temp);
                list.add(temp);
            }
        }
        return new ResponseEntity<List<GitHub>>(list, HttpStatus.OK);
    }
}
