package com.main.letstalk.repository;

import com.main.letstalk.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    public UserDetail findByUserId(int userId);

}
