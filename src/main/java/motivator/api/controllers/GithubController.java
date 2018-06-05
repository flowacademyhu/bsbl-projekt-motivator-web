package motivator.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.CommitService;
import org.kohsuke.github.GHCommit;
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
        private List<String> fileNames;   // CHANGED FILENAME + CONTENT OF FILE

        @Override
        public String toString() {
            return "GitHub{" +
                    "commitShal='" + commitShal + '\'' +
                    ", commitMessage='" + commitMessage + '\'' +
                    ", commitDate=" + commitDate +
                    ", fileMap=" + fileNames;
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
        org.kohsuke.github.GitHub connectionGitHub = org.kohsuke.github.GitHub.connectUsingPassword("USERNAME", "PASSWORD");

        for (Collection<RepositoryCommit> commits : service.pageCommits(repo, size)) {
            for (RepositoryCommit commit : commits) {
                GitHub temp = new GitHub();
                String shal = commit.getSha().substring(0, 7);
                temp.commitShal = shal;
                temp.commitMessage = commit.getCommit().getMessage();
                temp.commitDate = commit.getCommit().getAuthor().getDate();
                List<GHCommit.File> files = connectionGitHub.getRepository(FULL_REPOSITORY).getCommit(shal).getFiles();
                for (GHCommit.File file : files) {
                    String fileName = null;
                    fileName = file.getFileName();
                    try {
                        temp.fileNames.add(fileName);
                    } catch (NullPointerException e) {
                        System.err.println("No filename!");
                    }
                }
                System.out.println(temp);
                list.add(temp);
            }
        }
        return new ResponseEntity<List<GitHub>>(list, HttpStatus.OK);
    }
}
