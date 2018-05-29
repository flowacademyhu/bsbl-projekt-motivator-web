package motivator.api.controllers;

import io.jsonwebtoken.*;
import motivator.api.models.User;
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
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(User user) throws NameAlreadyBoundException {
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
	public String login(User login) throws ServletException {

		String jwtToken = "";

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
		jwtToken = createToken(jwtToken, email);

		return jwtToken;
	}

	public String createToken(String jwtToken, String email) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		long expMillis = nowMillis + (5 * 60 * 1000);
		Date exp = new Date(expMillis);

		jwtToken = Jwts.builder()
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
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	return jwtToken;
	}
}
