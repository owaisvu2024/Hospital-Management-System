package com.Hospital_System.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class InsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long insuranceId;
    private String policyNo;
    private String insuranceProvider;
    private LocalDate validUntil;
    @CreationTimestamp
    private LocalDateTime insuranceDate;

}
