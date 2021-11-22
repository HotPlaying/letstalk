package com.trd.letstalk.service;

import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserDetail;

public interface UserDetailService {
    public UserDetail findByUserId(int userId);
    public UserDetail findByUser(User user);
    public void save(UserDetail userDetail);
}
