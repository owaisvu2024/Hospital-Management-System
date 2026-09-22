package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.RegisterDto;
import com.Hospital_Managment.demo.Entity.UserCredentials;
import com.Hospital_Managment.demo.Servics.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    @Autowired
    private RegisterService userCredentials_Servics;


    @PostMapping("/Register")
    public UserCredentials userRegister(@Valid @RequestBody RegisterDto dto){
        return userCredentials_Servics.userRegister(dto);
    }


}
