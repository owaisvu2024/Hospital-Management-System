package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Config.JwtUtil;
import com.Hospital_Managment.demo.Dto.RegisterDto;
import com.Hospital_Managment.demo.Entity.UserCredentials;
import com.Hospital_Managment.demo.Repository.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String login(RegisterDto userCredentialsDto) {
        UserCredentials userCredentials = userCredentialsRepo.findByUsername(userCredentialsDto.getUsername());

        if (userCredentials==null) {
            throw new RuntimeException("Invalid username or password");
        }
        boolean passwordMatch = passwordEncoder.matches(userCredentialsDto.getPassword(), userCredentials.getPassword());
        if (!passwordMatch) {
            throw new RuntimeException("Invalid username or password");

        }
        return jwtUtil.generateToken(userCredentials.getUsername());
    }
}
