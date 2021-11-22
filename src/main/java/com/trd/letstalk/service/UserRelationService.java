package com.trd.letstalk.service;

import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserRelation;

import java.util.List;

public interface UserRelationService {
    public void save(UserRelation userRelation);
    public void delete(UserRelation userRelation);
    public List<User> findByUserId(int userId);
}
