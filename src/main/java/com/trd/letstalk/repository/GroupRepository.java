package com.trd.letstalk.repository;

import com.trd.letstalk.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    public Group findByGroupId(int groupId);
}
