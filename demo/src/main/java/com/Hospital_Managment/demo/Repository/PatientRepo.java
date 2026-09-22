package com.Hospital_Managment.demo.Repository;

import com.Hospital_Managment.demo.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<PatientEntity,Long > {
boolean existsByEmail( String Email);
}
