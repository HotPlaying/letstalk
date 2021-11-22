package com.trd.letstalk.repository;

import com.trd.letstalk.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    public UserDetail findByUserId(int userId);

}
