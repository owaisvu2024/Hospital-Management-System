package com.Hospital_Managment.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorDto {
    @NotBlank(message = "Doctor name is required")
    private String doctorName;
    @NotBlank(message = "Doctor email is required")
    @Email(message = "Write correct email format")
    private String doctorEmail;
    @NotBlank(message = "Doctor phone no is required")
    private String doctorPhone;
    @NotBlank(message = "Doctor specialization is required")
    private String specialization;
}
