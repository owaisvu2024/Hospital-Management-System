package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.RegisterDto;
import com.Hospital_Managment.demo.Servics.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
   private LoginService services ;

    @PostMapping("/post")
    public String login(@Valid @RequestBody RegisterDto dto) {
        return services.login(dto);
    }




}
