package com.Hospital_System.demo.Repository;

import com.Hospital_System.demo.Entity.DepartmentEntity;
import com.Hospital_System.demo.Entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<DoctorEntity,Long> {
Long countByDepartment(DepartmentEntity department);

}

