package com.Hospital_System.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String status;

    @CreationTimestamp
    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private PatientEntity patient;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private DoctorEntity doctor;



}
