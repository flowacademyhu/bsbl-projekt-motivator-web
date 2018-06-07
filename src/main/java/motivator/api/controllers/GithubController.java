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
        Integer id;
        String commitShal;          // COMMIT SHAL
        String commitMessage;       // COMMIT MESSAGE
        Date commitDate;          // COMMIT DATE
        private HashMap<String, Integer> changes = new HashMap<>();   // CHANGED FILENAME + CONTENT OF FILE

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
    public @ResponseBody ResponseEntity getGithubInfo(@RequestHeader(value = "Authorization") String Authorization) throws IOException {

        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        List<GitHub> list = new ArrayList<>();

        final int size = 1;
        final RepositoryId repo = new RepositoryId(OWNER, REPOSITORY);
        final CommitService service = new CommitService();
        int counter = 0;

        for (Collection<RepositoryCommit> commits : service.pageCommits(repo, size)) {
            for (RepositoryCommit commit : commits) {
                counter++;
                GitHub temp = new GitHub();
                temp.id = counter;
                String shal = commit.getSha().substring(0, 7);
                temp.commitShal = shal;
                temp.commitMessage = commit.getCommit().getMessage();
                temp.commitDate = commit.getCommit().getAuthor().getDate();
                list.add(temp);
            }
        }
        return ResponseEntity.ok(list.toString());
    }
}
