package com.Hospital_System.demo.Repository;

import com.Hospital_System.demo.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<PatientEntity, Long> {

}
