package com.trd.letstalk.repository;

import com.trd.letstalk.entity.UserGroupRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRelationRepository extends JpaRepository<UserGroupRelation, Integer> {
    public List<UserGroupRelation> findByUserId(int userId);
    public List<UserGroupRelation> findByGroupId(int groupId);
}
