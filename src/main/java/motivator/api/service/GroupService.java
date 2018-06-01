package motivator.api.service;

import motivator.api.models.Group;

public interface GroupService {
    Group save(Group group);
    Group findByName(String name);
    Group deleteByName(String name);
}
