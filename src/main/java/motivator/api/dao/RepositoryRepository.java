package motivator.api.dao;

import motivator.api.models.Repository;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface RepositoryRepository extends CrudRepository<Repository, Long> {
    Repository findByRepoName(String name);
    Repository findByOwner(String owner);
}
