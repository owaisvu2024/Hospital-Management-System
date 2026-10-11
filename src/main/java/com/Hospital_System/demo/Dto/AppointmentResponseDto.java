package com.Hospital_System.demo.Dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AppointmentResponseDto {
    private Long appointmentId;
    private String status;
    private LocalDate appointmentDate;
    private Long  doctorId;
    private String doctorName;


    private Long patientId;
    private String patientName;



}
