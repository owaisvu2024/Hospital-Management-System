package com.Hospital_Managment.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String bloodGroup;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Insurance_Id")
    private Insurance insurance;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appiontment;


}
