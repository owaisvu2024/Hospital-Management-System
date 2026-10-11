package com.Hospital_System.demo.Repository;

import com.Hospital_System.demo.Dto.DepartmentDto;
import com.Hospital_System.demo.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Long> {
}
