package com.trd.letstalk.service.impl;

import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserRelation;
import com.trd.letstalk.repository.UserRelationRepository;
import com.trd.letstalk.repository.UserRepository;
import com.trd.letstalk.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Autowired
    private UserRelationRepository userRelationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserRelation userRelation) {
        userRelationRepository.save(userRelation);
    }

    @Override
    public void delete(UserRelation userRelation) {
        userRelationRepository.delete(userRelation);
    }

    @Override
    public List<User> findByUserId(int userId) {
        List<UserRelation> uRList = userRelationRepository.findByUserId(userId);
        List<User> userList= new ArrayList<>();
        for (int i = 0; i < uRList.size(); i++) {
            int idA = uRList.get(i).getUserIdA();
            int idB = uRList.get(i).getUserIdB();
            userList.add(userRepository.findByUserId(idA == userId ? idB:idA));
        }
        return userList;
    }
}
