package com.Hospital_Managment.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {
   @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;
    @NotNull(message = "BirthDate is required")
    private LocalDate birthDate;
    @NotBlank(message = "Email is required")
    @Email(message = "Your email format i wrong")
    private String email;
    @NotBlank(message = "BloodGroup is required")
    private String bloodGroup;

    private Long insuranceId;
}
