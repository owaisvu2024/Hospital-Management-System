package com.Hospital_Managment.demo.Repository;

import com.Hospital_Managment.demo.Entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Long> {
UserCredentials findByUsername(String username);

}
