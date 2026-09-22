package com.Hospital_Managment.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDto {
    @NotBlank(message = "Department name is required")
    private String name;
    private Long doctorId;
}
