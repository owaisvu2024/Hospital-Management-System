package com.Hospital_System.demo.Dto;

import com.Hospital_System.demo.Entity.DoctorEntity;
import lombok.Data;

@Data
public class DoctorResponsDto {

    private Long doctorId;
    private String doctorName;
    private String email;
    private String specialization;
    private Long departId;

    private String shifTiming;
    private int maxPatientPerDay;


}
