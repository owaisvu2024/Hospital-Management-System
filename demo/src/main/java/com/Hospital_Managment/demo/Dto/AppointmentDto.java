package com.Hospital_Managment.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDto {
    @NotBlank(message = "Reason is required")
    private String reason;
    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Patient id is required")
    private Long patientId;

    @NotNull(message = "DoctorId is required")
    private Long doctorId;


}
