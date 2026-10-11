package com.Hospital_System.demo.Dto;

import com.Hospital_System.demo.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    @NotBlank(message = "Username is required")
   private String  userName;
    @NotBlank(message = "Password is required")
    private String  Password;

    private Set<Role> roles;

}
