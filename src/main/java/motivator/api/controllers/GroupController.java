package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.dao.GroupAdminRepository;
import motivator.api.dao.GroupRepository;
import motivator.api.dao.GroupUserRepository;
import motivator.api.models.Group;
import motivator.api.models.GroupUser;
import motivator.api.models.User;
import motivator.api.models.GroupAdmin;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;

import javax.naming.NameAlreadyBoundException;

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
        User user = userService.findByEmail(email);
        Group newGroup = new Group();
        newGroup.setName(group.getName());
        if (groupService.findByName(group.getName()) != null) {
            throw new NameAlreadyBoundException("Group name is already in use.");
        }
        newGroup.setGitHubGrupRep(group.getGitHubGrupRep());
        newGroup.setTrelloGroup(group.getTrelloGroup());
        newGroup.setSlackGroupHook(group.getSlackGroupHook());
        user.setActiveGroup(newGroup.getName());
        setAdmin(user, newGroup);
        setMember(user, newGroup);
        return groupService.save(newGroup);

    }

    public GroupAdmin setAdmin(User user, Group group) {
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.setUser(user);
        groupAdmin.setGroup(group);
        groupAdminRepository.save(groupAdmin);
        return groupAdmin;
    }

    public GroupUser setMember(User user, Group group) {
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

    @RequestMapping(value = "/app/currentuser/groups/profile/edit", method = RequestMethod.POST)
    public ResponseEntity<Group> updateGroup(@RequestHeader (value = "Authorization") String jwtToken, @RequestBody Group group) {
        jwtToken = jwtToken.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();

        User user = userService.findByEmail(claims.getSubject());
        String activeGroup = user.getActiveGroup();
        Group groupDb = groupService.findByName(activeGroup);
        groupDb.setName(group.getName());
        groupDb.setGitHubGrupRep(group.getGitHubGrupRep());
        groupDb.setTrelloGroup(group.getTrelloGroup());
        groupDb.setSlackGroupHook(group.getSlackGroupHook());
        groupService.save(groupDb);
        user.setActiveGroup(group.getName());
        userService.save(user);

        return new ResponseEntity <Group>(groupDb, HttpStatus.OK);
    }

    @RequestMapping(value = "/app/currentuser/groups/profile/edit/new/member", method = RequestMethod.POST)
    public Group addNewMember(@RequestHeader (value = "Authorization") String jwtToken, @RequestBody String addUser) {
        jwtToken = jwtToken.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();

        User user = userService.findByEmail(claims.getSubject());
        String activeGroup = user.getActiveGroup();
        Group groupDb = groupService.findByName(activeGroup);

        User addNewUser = userService.findByEmail(addUser);
        setMember(addNewUser, groupDb);
        System.err.println(addUser);
        return groupService.save(groupDb);
    }
}
