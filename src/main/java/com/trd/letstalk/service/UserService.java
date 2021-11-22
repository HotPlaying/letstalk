package com.trd.letstalk.service;

import com.trd.letstalk.entity.User;

import java.util.List;

public interface UserService {
    public void save(User user);
    public List<User> findAllByUserId(int userId);
    public User findByUserId(int userId);
    public User checkUser(User user);
}
