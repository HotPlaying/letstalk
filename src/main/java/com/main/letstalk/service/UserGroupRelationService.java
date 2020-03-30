package com.main.letstalk.service;

import com.main.letstalk.entity.Group;
import com.main.letstalk.entity.UserGroupRelation;

import java.util.List;

public interface UserGroupRelationService {
    public List<Group> findByUserId(int userId);
    public List<UserGroupRelation> findByGroupId(int groupId);
    public void save(UserGroupRelation userGroupRelation);
}
