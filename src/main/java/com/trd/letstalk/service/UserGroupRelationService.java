package com.trd.letstalk.service;

import com.trd.letstalk.entity.Group;
import com.trd.letstalk.entity.UserGroupRelation;

import java.util.List;

public interface UserGroupRelationService {
    public List<Group> findByUserId(int userId);
    public List<UserGroupRelation> findByGroupId(int groupId);
    public void save(UserGroupRelation userGroupRelation);
}
