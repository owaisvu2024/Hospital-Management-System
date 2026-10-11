package com.Hospital_System.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDto {
    @NotBlank(message = "DepartName is required")
    private String departName;
    private Long doctorId;




}
