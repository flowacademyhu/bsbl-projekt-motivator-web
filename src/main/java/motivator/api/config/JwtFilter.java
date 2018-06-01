package motivator.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import motivator.api.controllers.UserController;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
	UserController userController = new UserController();

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		String token = "";

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = (request).getHeader("Authorization");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers","Authorization");
		response.setHeader("Authorization", "Bearer " + authHeader.substring(7));

		System.out.println("AuthHeader: " + authHeader);

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header");
			}
			try {
				token = authHeader.substring(7);
				Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				userController.createToken(claims.getSubject());
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {
				throw new ServletException("Invalid token");
			}
			chain.doFilter(req, res);
		}
	}
}
