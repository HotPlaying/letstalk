package com.main.letstalk.service;

import com.main.letstalk.entity.User;
import com.main.letstalk.entity.UserRelation;

import java.util.List;

public interface UserRelationService {
    public void save(UserRelation userRelation);
    public void delete(UserRelation userRelation);
    public List<User> findByUserId(int userId);
}
