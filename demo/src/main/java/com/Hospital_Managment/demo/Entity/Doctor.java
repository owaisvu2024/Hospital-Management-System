package com.Hospital_Managment.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long doctorId;
    private String doctorName;
    private String doctorEmail;
    private String doctorPhone;
    private String specialization;

    @CreationTimestamp
    private LocalDateTime createAt;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appiontment;


   @ManyToMany
    @JoinTable(
       name="Doctor_Department",
       joinColumns=@JoinColumn(name = "Doctor_Id"),
       inverseJoinColumns=@JoinColumn(name = "Department_Id")
   )
   private List<Department> department;

}
