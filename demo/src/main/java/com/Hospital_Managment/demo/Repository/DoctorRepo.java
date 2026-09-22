package com.Hospital_Managment.demo.Repository;

import com.Hospital_Managment.demo.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    boolean existsByDoctorEmail( String doctorEmail);
}
