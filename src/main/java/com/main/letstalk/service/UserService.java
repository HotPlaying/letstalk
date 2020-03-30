package com.main.letstalk.service;

import com.main.letstalk.entity.User;

import java.util.List;

public interface UserService {
    public void save(User user);
    public List<User> findAllByUserId(int userId);
    public User findByUserId(int userId);
    public User checkUser(User user);
}
