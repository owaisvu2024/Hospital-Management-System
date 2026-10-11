package com.Hospital_System.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private String bloodType;
    @CreationTimestamp
    private LocalDateTime createAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insuranceId")
    private InsuranceEntity insuranceEntity;

    @OneToMany(mappedBy = "patient")
    private List<AppointmentEntity> appointment;



}
