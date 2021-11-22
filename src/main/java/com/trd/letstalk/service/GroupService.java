package com.trd.letstalk.service;

import com.trd.letstalk.entity.Group;

public interface GroupService {
    public Group save(Group group);
    public Group findByGroupId(int groupId);
}
