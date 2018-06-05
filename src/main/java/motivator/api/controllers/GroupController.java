package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.dao.GroupAdminRepository;
import motivator.api.dao.GroupRepository;
import motivator.api.models.Group;
import motivator.api.models.User;
import motivator.api.models.GroupAdmin;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;

import javax.naming.NameAlreadyBoundException;

@CrossOrigin(origins = "http://localhost/curentuser/groups", maxAge = 3600)
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    private UserService userService;
    private GroupAdminRepository groupAdminRepository;
    private GroupRepository groupRepository;

    @RequestMapping(value = "/app/createGroup/currentuser", method = RequestMethod.POST)
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

        String email = claims.getSubject();

        User user = userService.findByEmail(email);
        setAdmin(user, newGroup);
        return groupService.save(newGroup);
    }

    public GroupAdmin setAdmin(User user, Group group) {
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.setUser(user);
        groupAdmin.setGroup(group);
        return groupAdminRepository.save(groupAdmin);
    }
/*
    @RequestMapping(path="/all", method = RequestMethod.GET)
    public ArrayList<Group> getAllGroup() {
        //return groupRepository.findAll();
    }
*/
    @RequestMapping(path="/delete", method = RequestMethod.DELETE)
    public Group deleteGroup(@RequestBody Group group) {
        return groupService.deleteByName(group.getName());
    }

    @RequestMapping(path="/update", method = RequestMethod.PUT)
    public Group updateGroup(@PathVariable("name") String name, @RequestBody Group group, User user) {

        //Group currentGroup = GroupService.findByName(group.getName());
        //currentGroup.setGroup();
        //currentGroup.setUser();

        return groupService.deleteByName(group.getName());
    }
}
