package com.Hospital_System.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departId;
    private String departName;

    @CreationTimestamp
    private LocalDateTime CreateAt;

    @OneToOne
    @JoinColumn(name = "head_Doctor")
    private DoctorEntity doctor;

    @ManyToMany(mappedBy = "departments")
    private List<DoctorEntity> doctors;




}
