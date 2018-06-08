package motivator.api.controllers;

import motivator.api.dao.UserRepository;
import motivator.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/user/users")
	public String loginSuccess() { return "This is a secured page. Login was successful! If you see this message, your token is valid"; }

	@RequestMapping(value = "/user/email", method = RequestMethod.POST)
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@RequestMapping(value = "/app/currentuser/update", method = RequestMethod.POST)
	public User updateUser(User user) {
		User editUser = findByEmail(user.getEmail());
		System.out.println(editUser);
		editUser.setName(user.getName());
		editUser.setPassword(user.getPassword());
		editUser.setGitHubProfile(user.getGitHubProfile());
		editUser.setTrelloProfile(user.getTrelloProfile());
		editUser.setSlackProfile(user.getSlackProfile());
		editUser.setCurrentScore(findByEmail(user.getEmail()).getCurrentScore());
		return userRepository.save(editUser);
	}
}
