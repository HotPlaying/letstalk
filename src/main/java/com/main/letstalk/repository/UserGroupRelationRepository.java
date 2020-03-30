package com.main.letstalk.repository;

import com.main.letstalk.entity.UserGroupRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRelationRepository extends JpaRepository<UserGroupRelation, Integer> {
    public List<UserGroupRelation> findByUserId(int userId);
    public List<UserGroupRelation> findByGroupId(int groupId);
}
