package com.Hospital_Managment.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceDto {
    @NotBlank(message = "policyNumber is required")
    private String policyNumber;
    @NotBlank(message = "provider is required")
    private String provider;
    @NotNull(message = "validUntil is required")
    private LocalDate validUntil;


}
