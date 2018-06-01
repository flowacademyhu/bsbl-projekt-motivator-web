package motivator.api.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import motivator.api.models.Group;
import motivator.api.models.User;
import motivator.api.service.GroupService;
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
public class HomeController {
    private SessionFactory factory;

    private class Home {
        private String groupName;
        private List<String> admins;
    }
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    public ResponseEntity<List<Home>> getInfo (@RequestHeader String Authorization) {
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        List<Home> list = new ArrayList<>();

        Session session = factory.getCurrentSession();
        String grpQuery = "SELECT motivator.groups.name FROM motivator.groups " +
                "right join motivator.group_user on motivator.groups.id = motivator.group_user.group_id " +
                "left join motivator.user on motivator.group_user.user_id = motivator.user.id" +
                "where motivator.user.name = :name;";
        SQLQuery grpSql = session.createSQLQuery(grpQuery);
        grpSql.setParameter("name", user.getName());
        List grpList = grpSql.list();

        for (Object gItem: grpList) {
            Group grp = (Group) gItem;
            Home temp = new Home();
            String grpName = grp.getName();
            temp.groupName = grpName;

            String adminQuery = "Select motivator.user.name from motivator.user " +
                    "right join motivator.group_admin on motivator.user.id = motivator.group_admin.id " +
                    "left join motivator.groups on motivator.group_admin.id = motivator.groups.id " +
                    "where motivator.groups.name = :groupName;";
            SQLQuery adminSql = session.createSQLQuery(adminQuery);
            adminSql.setParameter("groupName", grpName);
            List adminList = adminSql.list();

            for (Object aItem: adminList) {
                User admin = (User) aItem;
                String adminName = admin.getName();

                temp.admins.add(adminName);
            }
            list.add(temp);
        }

        return new ResponseEntity<List<Home>>(list, HttpStatus.OK);
    }
}
