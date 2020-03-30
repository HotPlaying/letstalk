package com.main.letstalk.service;

import com.main.letstalk.entity.User;
import com.main.letstalk.entity.UserDetail;

public interface UserDetailService {
    public UserDetail findByUserId(int userId);
    public UserDetail findByUser(User user);
    public void save(UserDetail userDetail);
}
