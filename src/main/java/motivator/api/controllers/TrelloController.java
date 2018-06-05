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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class TrelloController {
    private class Trello {
        private String trelloUrl;
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/app/currentuser/activegroup/trello", method = RequestMethod.GET)
    public ResponseEntity<Trello> getActiveGroupTable(@RequestHeader(value = "Authorization") String Authorization) {
        Authorization = Authorization.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(Authorization).getBody();
        User user = userService.findByEmail(claims.getSubject());

        String activeGroup = user.getActiveGroup();
        String activeTrello = "";

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        String actQuery = "SELECT groups.trello_group FROM groups " +
                        "right join group_user on groups.id = group_user.group_id " +
                        "left join user on group_user_id = user.id " +
                        "where group.name = :activeGroup";
        SQLQuery actSql = session.createSQLQuery(actQuery);
        actSql.setParameter("activeGroup", activeGroup);
        List result = actSql.list();

        for (Object actItem : result) {
            activeTrello = ((Group) actItem).getTrelloGroup();
        }
        Trello trello = new Trello();
        trello.trelloUrl = activeTrello;
        session.close();

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Authorization", "Bearer " + Authorization);
        responseHeader.add("Trello", activeTrello);
        return new ResponseEntity<Trello>(trello, responseHeader, HttpStatus.OK);
    }
}
