package com.Hospital_System.demo.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponseDto {
    private Long patientId;
    private String name;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private String bloodType;
    private Long insuranceId;
    private LocalDateTime createAt;

}
