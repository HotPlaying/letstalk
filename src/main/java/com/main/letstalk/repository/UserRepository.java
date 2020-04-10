package com.main.letstalk.repository;

import com.main.letstalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUserName(String userName);

    public List<User> findAllByUserId(int userId);

    public User findByUserId(int userId);
}
