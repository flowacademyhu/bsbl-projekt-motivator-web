package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.models.Group;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.naming.NameAlreadyBoundException;
import javax.servlet.ServletException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost/curentuser/groups", maxAge = 3600)
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    private UserService userService;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Group createUser(@RequestBody Group group, @RequestHeader String jwtToken) throws NameAlreadyBoundException {
        Group newGroup = new Group();
        newGroup.setName(group.getName());
        if (groupService.findByName(group.getName()) != null) {
            throw new NameAlreadyBoundException("Group name is already in use.");
        }
        newGroup.setGitHubGrupRep(group.getGitHubGrupRep());
        newGroup.setTrelloGroup(group.getTrelloGroup());
        newGroup.setSlackGroupHook(group.getSlackGroupHook());

        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();

        String emailFromToken = claims.getSubject();

        userService.findByEmail(email)

        return groupService.save(newGroup);
    }
}
