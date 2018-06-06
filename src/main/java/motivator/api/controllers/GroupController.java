package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.config.HibernateUtil;
import motivator.api.dao.GroupAdminRepository;
import motivator.api.dao.GroupRepository;
import motivator.api.dao.GroupUserRepository;
import motivator.api.models.Group;
import motivator.api.models.GroupUser;
import motivator.api.models.User;
import motivator.api.models.GroupAdmin;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;

import javax.naming.NameAlreadyBoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupAdminRepository groupAdminRepository;
    @Autowired
    private GroupUserRepository groupUserRepository;
    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping(value = "/app/currentuser/groups/create", method = RequestMethod.POST)
    public Group createGroup(@RequestBody Group group, @RequestHeader (value = "Authorization") String Authorization)
            throws NameAlreadyBoundException {
        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();

        String email = claims.getSubject();
        System.err.println(email);

        User user = userService.findByEmail(email);
        Group newGroup = new Group();
        newGroup.setName(group.getName());
        if (groupService.findByName(group.getName()) != null) {
            throw new NameAlreadyBoundException("Group name is already in use.");
        }
        newGroup.setGitHubGrupRep(group.getGitHubGrupRep());
        newGroup.setTrelloGroup(group.getTrelloGroup());
        newGroup.setSlackGroupHook(group.getSlackGroupHook());

        setAdmin(user, newGroup);
        setUser(user, newGroup);
        return groupService.save(newGroup);

    }

    public GroupAdmin setAdmin(User user, Group group) {
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.setUser(user);
        groupAdmin.setGroup(group);
        groupAdminRepository.save(groupAdmin);
        return groupAdmin;
    }

    public GroupUser setUser(User user, Group group) {
        GroupUser groupUser = new GroupUser();
        groupUser.setUser(user);
        groupUser.setGroup(group);
        groupUserRepository.save(groupUser);
        return groupUser;
    }

    @RequestMapping(value = "/app/currentuser/groups/profile", method = RequestMethod.GET)
    public ResponseEntity<Group> groupProfile(@RequestHeader (value = "Authorization") String jwtToken)  {
        jwtToken = jwtToken.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();

        User user = userService.findByEmail(claims.getSubject());
        String activeGroup = user.getActiveGroup();
        Group group = groupService.findByName(activeGroup);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }

/*
    @RequestMapping(path="/all", method = RequestMethod.GET)
    public ArrayList<Group> getAllGroup() {
        //return groupRepository.findAll();
    }

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
*/
}
