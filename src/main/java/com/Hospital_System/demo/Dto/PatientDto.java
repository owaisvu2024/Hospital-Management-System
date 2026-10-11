package com.Hospital_System.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {
    @NotBlank(message = "Patient name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter email correct format")
    private String email;

    @NotNull(message = "DateOfBirth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "BloodType is required")
    private String bloodType;
    private Long insuranceId;


}
