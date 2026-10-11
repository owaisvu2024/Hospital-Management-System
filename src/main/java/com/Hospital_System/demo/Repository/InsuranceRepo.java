package com.Hospital_System.demo.Repository;

import com.Hospital_System.demo.Entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepo extends JpaRepository<InsuranceEntity,Long> {
}
