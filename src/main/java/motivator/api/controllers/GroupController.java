package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.dao.GroupAdminRepository;
import motivator.api.dao.GroupRepository;
import motivator.api.dao.GroupUserRepository;
import motivator.api.models.*;
import motivator.api.service.GroupService;
import motivator.api.service.RepositoryService;
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
    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(value = "/app/currentuser/groups/create/repo", method = RequestMethod.POST)
    public Repository addRepository(@RequestBody Repository repository, @RequestHeader (value = "Authorization") String Authorization) {
        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());
        Group group = groupService.findByName(user.getActiveGroup());

        Repository newRepository = new Repository();
        newRepository.setOwner(repository.getOwner());
        newRepository.setRepoName(repository.getRepoName());
        newRepository.setGroup(group);
        return repositoryService.save(newRepository);
    }

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
        System.err.println(group);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "/app/currentuser/groups/profile/edit", method = RequestMethod.POST)
    public ResponseEntity<Group> updateGroup(@RequestHeader (value = "Authorization") String jwtToken, @RequestBody Group group, User userOfGroup) {
        jwtToken = jwtToken.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();

        User user = userService.findByEmail(claims.getSubject());
        String activeGroup = user.getActiveGroup();
        Group groupDb = groupService.findByName(activeGroup);
        System.err.println(user);
        groupDb.setName(group.getName());
        groupDb.setGitHubGrupRep(group.getGitHubGrupRep());
        groupDb.setTrelloGroup(group.getTrelloGroup());
        groupDb.setSlackGroupHook(group.getSlackGroupHook());
        groupService.save(groupDb);
        user.setActiveGroup(group.getName());
        userService.save(user);
        System.err.println(groupDb);
        return new ResponseEntity <Group>(groupDb, HttpStatus.OK);
    }
}
