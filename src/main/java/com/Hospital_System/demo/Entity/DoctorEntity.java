package com.Hospital_System.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    private String email;
    private String specialization;
    private String shifTiming;
    private int maxPatientPerDay;


    @CreationTimestamp
   private LocalDateTime createAt;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<AppointmentEntity> appointment;


    @OneToOne(mappedBy = "doctor")
    private  DepartmentEntity department;

    @ManyToMany

    @JoinTable(
            name = "Doctor_Depart",
            joinColumns  = @JoinColumn(name = "doctor_Id"),
            inverseJoinColumns = @JoinColumn(name = "depart_Id")
    )
    private List<DepartmentEntity> departments;


}
