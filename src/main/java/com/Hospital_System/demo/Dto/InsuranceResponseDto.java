package com.Hospital_System.demo.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InsuranceResponseDto {
    private Long insuranceId;
    private String policyNo;
    private String insuranceProvider;
    private LocalDate validUntil;
    private LocalDateTime insuranceDate;

}
