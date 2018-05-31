package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.models.Group;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.naming.NameAlreadyBoundException;
import javax.servlet.ServletException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public Group createUser(@RequestBody Group group){
        Group newGroup = new Group();
        newGroup.setName(group.getName());
        newGroup.setGitHubGrupRep(group.getGitHubGrupRep());
        newGroup.setTrelloGroup(group.getTrelloGroup());
        newGroup.setSlackGroupHook(group.getSlackGroupHook());
        return groupService.save(newGroup);
    }
}
