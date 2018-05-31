package motivator.api.service.impl;

import motivator.api.dao.GroupRepository;
import motivator.api.dao.UserRepository;
import motivator.api.models.Group;
import motivator.api.models.User;
import motivator.api.service.GroupService;
import motivator.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group findByName(String name) { return groupRepository.findByName(name); }

    public Group findAll() { return groupRepository.findAll(); }

    public Group deleteByName(String name) { return groupRepository.deleteByName(name); }
}

