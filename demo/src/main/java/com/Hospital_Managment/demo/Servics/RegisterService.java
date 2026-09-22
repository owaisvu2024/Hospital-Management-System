package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Dto.RegisterDto;
import com.Hospital_Managment.demo.Entity.UserCredentials;
import com.Hospital_Managment.demo.Repository.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCredentials userRegister(RegisterDto dto) {
        if (userCredentialsRepo.findByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("Username Already Exists");
        }


        UserCredentials user = new UserCredentials();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole()!=null?dto.getRole():"user");
        return userCredentialsRepo.save(user);


    }
}
