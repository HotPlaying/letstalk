package com.trd.letstalk.service.impl;

import com.trd.letstalk.entity.Group;
import com.trd.letstalk.entity.UserGroupRelation;
import com.trd.letstalk.repository.GroupRepository;
import com.trd.letstalk.repository.UserGroupRelationRepository;
import com.trd.letstalk.service.UserGroupRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupRelationServiceImpl implements UserGroupRelationService {
    @Resource
    UserGroupRelationRepository uGRR;
    @Resource
    GroupRepository groupRepository;

    @Override
    public List<Group> findByUserId(int userId) {
        List<UserGroupRelation> ugrList = uGRR.findByUserId(userId);
        List<Group> groupList = new ArrayList<>();
        for (UserGroupRelation ugr : ugrList) {
            groupList.add(groupRepository.findByGroupId(ugr.getGroupId()));
        }
        return groupList;
    }

    @Override
    public List<UserGroupRelation> findByGroupId(int groupId) {
        return uGRR.findByGroupId(groupId);
    }

    @Override
    public void save(UserGroupRelation userGroupRelation) {
        uGRR.save(userGroupRelation);
    }
}
