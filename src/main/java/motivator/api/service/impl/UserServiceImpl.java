package motivator.api.service.impl;

import motivator.api.dao.UserDao;
import motivator.api.models.User;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User save(User user) {
		return userDao.save(user);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
}
