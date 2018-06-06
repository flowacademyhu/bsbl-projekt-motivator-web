package motivator.api.dao;

import motivator.api.models.GroupUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, Long> {

}