package motivator.api.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import motivator.api.config.HibernateUtil;
import motivator.api.models.Group;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class HeaderController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/app/currentuser/activegroup", method = RequestMethod.POST)
    public ResponseEntity<String> updateActiveGroup (@RequestHeader(value = "Authorization") String Authorization, @RequestBody User input) {
        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        user.setActiveGroup(input.getActiveGroup());
        return new ResponseEntity<>("allright", HttpStatus.OK);
    }

    @RequestMapping(value = "/app/currentuser/activegroup", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getGroups (@RequestHeader(value = "Authorization") String Authorization) {
        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        List<String> groups = new ArrayList<>();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        String grpQuery = "SELECT groups.name FROM groups " +
                "left join group_user on groups.id = group_user.group_id " +
                "left join user on group_user.user_id = user.id " +
                "where user.name = :userName";
        SQLQuery grpSql = session.createSQLQuery(grpQuery);
        grpSql.setParameter("userName", user.getName());
        List grpList = grpSql.list();

        for (Object gItem: grpList) {
            String groupName = (String) gItem;
            groups.add(groupName);
        }
        session.close();
        return ResponseEntity.ok(groups);
    }
}
