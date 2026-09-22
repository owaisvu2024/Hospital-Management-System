package com.Hospital_Managment.demo.Repository;

import com.Hospital_Managment.demo.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {

}
