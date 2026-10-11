package com.Hospital_System.demo.Repository;

import com.Hospital_System.demo.Entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String userName);
    UserEntity findByUserName(String userName);
}
