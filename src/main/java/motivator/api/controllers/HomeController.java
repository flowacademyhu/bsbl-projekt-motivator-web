package motivator.api.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import motivator.api.config.HibernateUtil;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class HomeController {
    private class Home {
        private String groupName;
        private ArrayList<String> admins = new ArrayList<>();

        private String listAdmins (ArrayList<String> admins) {
            String adminList = "";
            for (String admin: admins) {
                adminList = adminList.concat('\"' + admin + "\", ");
            }
            StringBuilder aList = new StringBuilder(adminList);
            aList.deleteCharAt(aList.lastIndexOf(","));
            System.out.println(aList.toString());
            return aList.toString();
        }

        @Override
        public String toString() {
            return "{" +
                    "\"groupName\": \"" + groupName + '\"' +
                    ", \"admins\": [" + listAdmins(admins) +
                    "]}";
        }
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/app/currentuser", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getInfo (@RequestHeader (value = "Authorization") String Authorization) {
        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        List<Home> list = new ArrayList<>();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        String grpQuery = "SELECT groups.name FROM groups " +
                "left join group_user on groups.id = group_user.group_id " +
                "left join user on group_user.user_id = user.id " +
                "where user.name = :userName";
        SQLQuery grpSql = session.createSQLQuery(grpQuery);
        grpSql.setParameter("userName", user.getName());
        ArrayList<String> grpList = new ArrayList<String>(grpSql.list());
        for (String gItem: grpList) {
            Home temp = new Home();
            temp.groupName = gItem;

            String adminQuery = "Select user.name from user " +
                    "left join group_admin on user.id = group_admin.user_id " +
                    "left join groups on group_admin.group_id = groups.id " +
                    "where groups.name = :groupName";
            SQLQuery adminSql = session.createSQLQuery(adminQuery);
            adminSql.setParameter("groupName", gItem);
            ArrayList<String> adminList = new ArrayList<String>(adminSql.list());
            for (String aItem: adminList) {
                temp.admins.add(aItem);
            }
            list.add(temp);
        }
        session.close();
        return ResponseEntity.ok(list.toString());
    }
}
