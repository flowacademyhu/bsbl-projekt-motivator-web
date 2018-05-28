package motivator.api.service;

import motivator.api.models.User;

public interface UserService {
	User save(User user);

	User findByEmail(String email);

}
