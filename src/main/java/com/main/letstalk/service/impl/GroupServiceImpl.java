package com.main.letstalk.service.impl;

import com.main.letstalk.entity.Group;
import com.main.letstalk.repository.GroupRepository;
import com.main.letstalk.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupRepository groupRepository;

    @Override
    public Group save(Group group) {
        if ((group.getGroupId()) == 0) {
            group.setCreatedTime(LocalDate.now());
            group.setMembersCount(1);
            group.setGroupMembers(String.valueOf(group.getCreatorId()));
        }
        return groupRepository.saveAndFlush(group);
    }



    @Override
    public Group findByGroupId(int groupId) {
        return groupRepository.findByGroupId(groupId);
    }
}
