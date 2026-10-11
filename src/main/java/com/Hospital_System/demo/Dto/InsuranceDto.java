package com.Hospital_System.demo.Dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceDto {
    @NotBlank(message = "PolicyNo is required")
    private String policyNo;
    @NotBlank(message = "InsuranceProvider is required")
    private String insuranceProvider;
    @FutureOrPresent(message = "Enter present date and future Date")
    @NotNull(message = "ValidUntil is required")
    private LocalDate validUntil;
}
