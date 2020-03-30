package com.main.letstalk.repository;

import com.main.letstalk.entity.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRelationRepository extends JpaRepository<UserRelation,Integer> {
    @Query("select distinct u from UserRelation u where  userIdA = ?1 or userIdB = ?1")
    List<UserRelation> findByUserId(int userId);
}
