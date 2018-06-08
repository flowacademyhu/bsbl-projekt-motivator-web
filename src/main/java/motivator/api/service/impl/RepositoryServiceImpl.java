package motivator.api.service.impl;

import motivator.api.dao.RepositoryRepository;
import motivator.api.models.Repository;
import motivator.api.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    RepositoryRepository repositoryRepository;

    public Repository save(Repository repository) {
        return repositoryRepository.save(repository);
    }

    public Repository findByRepoName(String name) {
        return repositoryRepository.findByRepoName(name);
    }

    public Repository findByOwner(String owner) {
        return repositoryRepository.findByOwner(owner);
    }
}
