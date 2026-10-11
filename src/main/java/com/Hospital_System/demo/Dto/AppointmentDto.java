package com.Hospital_System.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class AppointmentDto {
    @NotBlank(message = "Status is Required")
    private String status;
    @NotNull(message = "DoctorId is Required")
    private Long doctorId;
    @NotNull(message = "PatientId is Required")
    private Long  patientId;


}
