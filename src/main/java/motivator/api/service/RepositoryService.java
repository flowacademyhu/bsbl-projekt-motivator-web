package motivator.api.service;

import motivator.api.models.Repository;

public interface RepositoryService {
    Repository save(Repository repository);
    Repository findByRepoName(String name);
    Repository findByOwner(String owner);
}
