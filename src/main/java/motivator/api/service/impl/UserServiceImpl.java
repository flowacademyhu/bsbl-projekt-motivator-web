package motivator.api.service.impl;

import motivator.api.dao.UserRepository;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByName(String name) {
		return userRepository.findByName(name);
	}
}
