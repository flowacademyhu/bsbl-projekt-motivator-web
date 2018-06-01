package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.naming.NameAlreadyBoundException;
import javax.servlet.ServletException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) throws NameAlreadyBoundException {
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setPassword(user.getPassword());
		if (userService.findByEmail(user.getEmail()) != null) {
			throw new NameAlreadyBoundException("E-mail is already in use.");
		}
		newUser.setEmail(user.getEmail());
		newUser.setGitHubProfile(user.getGitHubProfile());
		newUser.setTrelloProfile(user.getTrelloProfile());
		newUser.setSlackProfile(user.getSlackProfile());
		newUser.setCurrentScore(0L);
		return userService.save(newUser);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User login) throws ServletException {

		if (login.getEmail() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in e-mail address and password");
		}

		String email = login.getEmail();
		String password = login.getPassword();

		User user = userService.findByEmail(email);

		if (user == null) {
			throw new ServletException("User e-mail address not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("Invalid login. Please check your e-mail address and/or password.");
		}
		ResponseEntity<String> responseEntity = createToken(email);

		return responseEntity;
	}

	public ResponseEntity<String> createToken(String email) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		long expMillis = nowMillis + (1440 * 60 * 1000);
		Date exp = new Date(expMillis);

		String jwtToken = Jwts.builder()
				.setSubject(email)
				.claim("roles", "user")
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact();

		System.out.println("Token: " + jwtToken);

		Claims claims = Jwts.parser()
				.setSigningKey("secretkey")
				.parseClaimsJws(jwtToken).getBody();
		System.out.println("E-mail address: " + claims.getSubject());
		System.out.println("Expiration date: " + claims.getExpiration());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers", "*");
		headers.add("Authorization", "Bearer " + jwtToken);
		return (new ResponseEntity<>(jwtToken, headers, HttpStatus.OK));
	}

    @RequestMapping(value = "/userProfileUpdate", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestHeader String jwtToken, @RequestBody User user)  {
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();
        String email = claims.getSubject();
	    User userDb = userService.findByEmail(email);

        userDb.setName(user.getName());
        userDb.setPassword(user.getPassword());
        userDb.setGitHubProfile(user.getGitHubProfile());
        userDb.setTrelloProfile(user.getTrelloProfile());
        userDb.setSlackProfile(user.getSlackProfile());

        return new ResponseEntity<User>(userDb, HttpStatus.OK);
	}

    @RequestMapping(value = "/userprofile", method = RequestMethod.GET)
    public ResponseEntity<User> userProfile(@RequestHeader String jwtToken)  {
        Claims claims = Jwts.parser()
                .setSigningKey("secretkey")
                .parseClaimsJws(jwtToken).getBody();
        String email = claims.getSubject();
        User userDb = userService.findByEmail(email);

        return new ResponseEntity<User>(userDb,HttpStatus.OK);
    }
}
