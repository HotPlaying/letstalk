package com.main.letstalk.service.impl;

import com.main.letstalk.entity.User;
import com.main.letstalk.repository.UserRepository;
import com.main.letstalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        if (user.getUserId() <= 0) {
            user.setUserRegtime(LocalDate.now());
        }
        userRepository.save(user);
    }

    @Override
    public List<User> findAllByUserId(int userId) {
        return userRepository.findAllByUserId(userId);
    }

    @Override
    public User findByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }


    @Override
    public User checkUser(User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUserName());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getUserPsw().equals(user.getUserPsw())) {
                return optionalUser.get();
            }
        }
        return null;
    }
}
