package com.main.letstalk.repository;

import com.main.letstalk.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    public Group findByGroupId(int groupId);
}
