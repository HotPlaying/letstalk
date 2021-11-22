package com.trd.letstalk.service.impl;

import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserDetail;
import com.trd.letstalk.repository.UserDetailRepository;
import com.trd.letstalk.service.UserDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Resource
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetail findByUserId(int userId) {
        return userDetailRepository.findByUserId(userId);
    }

    @Override
    public UserDetail findByUser(User user) {
        return userDetailRepository.findByUserId(user.getUserId());
    }

    @Override
    public void save(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }
}
