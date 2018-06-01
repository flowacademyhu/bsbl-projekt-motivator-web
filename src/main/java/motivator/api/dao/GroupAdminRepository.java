package motivator.api.dao;

import motivator.api.models.GroupAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupAdminRepository extends CrudRepository<GroupAdmin, Long> {

}
