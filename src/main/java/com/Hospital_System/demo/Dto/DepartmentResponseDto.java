package com.Hospital_System.demo.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentResponseDto {
    private Long departId;
    private String departName;
    private Long doctorId;
    private String doctorName;
    private Long countDoctor;
    private LocalDateTime CreateAt;


}
