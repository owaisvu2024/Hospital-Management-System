package com.Hospital_System.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class DoctorDto {
    @NotBlank(message = "DoctorName is required")
    private String doctorName;
    @NotBlank(message = "Doctor email is required")
    @Email(message = "Email enter correct format")
    private String email;
    @NotBlank(message = "Doctor specialization is required")
    private String specialization;
    private Long departId;
    @NotBlank(message = "ShiftingTiming is required")
    private String shifTiming;
    @NotNull (message = "Total Patient is required")
    private Integer maxPatientPerDay;


}
