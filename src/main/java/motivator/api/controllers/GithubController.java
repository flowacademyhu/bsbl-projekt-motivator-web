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
import org.eclipse.egit.github.core.service.RepositoryService;
import org.kohsuke.github.GHCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class GithubController {
    private static final String FULL_REPOSITORY = "laszlobalint/java";
    private static final String OWNER = "laszlobalint";
    private static final String REPOSITORY = "java";

    private class GitHub {
        private String commitShal;          // COMMIT SHAL
        private String commitMessage;       // COMMIT MESSAGE
        private Date commitDate;          // COMMIT DATE
        private HashMap<String, String> fileMap;   // CHANGED FILENAME + CONTENT OF FILE
    }

    @Autowired
    private UserService userService;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }

    @RequestMapping(value = "/app/github", method = RequestMethod.GET)
    public ResponseEntity<List<GitHub>> getGithubInfo(@RequestHeader(value = "Authorization") String Authorization) throws IOException {

        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        // RepositoryService service = new RepositoryService();
        // service.getClient().setCredentials("laszlobalint", "nigiri001");

        List<GitHub> list = new ArrayList<>();

        final int size = 1;
        final RepositoryId repo = new RepositoryId(OWNER, REPOSITORY);
        final CommitService service = new CommitService();
        org.kohsuke.github.GitHub connectionGitHub = org.kohsuke.github.GitHub.connect();
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
                    String content = null;
                    fileName = file.getFileName();
                    content = file.getPatch();
                    try {
                        System.out.println(content);
                        temp.fileMap.put(fileName, content);
                    } catch (NullPointerException e) {
                        System.err.println("Empty file error!");
                    }
                }
                list.add(temp);
            }
        }
        return new ResponseEntity<List<GitHub>>(list, HttpStatus.OK);
    }
}
