package com.main.letstalk.service;

import com.main.letstalk.entity.Group;

public interface GroupService {
    public Group save(Group group);
    public Group findByGroupId(int groupId);
}
