package com.Hospital_System.demo.Servics;

import com.Hospital_System.demo.Dto.UserDto;
import com.Hospital_System.demo.Entity.Role;
import com.Hospital_System.demo.Entity.UserEntity;
import com.Hospital_System.demo.Repository.UserRepo;
import com.Hospital_System.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;


    public UserEntity signup(UserDto userDto) {
        if (userRepo.existsByUserName(userDto.getUserName())) {
            throw new IllegalArgumentException("user already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userDto.getRoles()==null || userDto.getRoles().isEmpty()) {
            userEntity.setRoles(Set.of(Role.PATIENT));

        }
        else {
            userEntity.setRoles(userDto.getRoles());

        }


        return userRepo.save(userEntity);
    }

    public String login(UserDto userDto) {
        UserEntity userEntity = userRepo.findByUserName(userDto.getUserName());
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }

        boolean passwordMatches = passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("password does not match");
        }

        return jwtUtil.generateToken(userDto.getUserName(), userEntity.getRoles());

    }


}
