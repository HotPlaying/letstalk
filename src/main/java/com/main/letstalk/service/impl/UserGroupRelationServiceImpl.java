package com.main.letstalk.service.impl;

import com.main.letstalk.entity.Group;
import com.main.letstalk.entity.UserGroupRelation;
import com.main.letstalk.repository.GroupRepository;
import com.main.letstalk.repository.UserGroupRelationRepository;
import com.main.letstalk.service.UserGroupRelationService;
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
