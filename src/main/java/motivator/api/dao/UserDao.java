package motivator.api.dao;

import motivator.api.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	User save(User user);

	User findByEmail(String email);
}
