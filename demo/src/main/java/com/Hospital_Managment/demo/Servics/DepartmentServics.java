package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Dto.DepartmentDto;
import com.Hospital_Managment.demo.Entity.Department;
import com.Hospital_Managment.demo.Entity.Doctor;
import com.Hospital_Managment.demo.Repository.DepartmentRepo;
import com.Hospital_Managment.demo.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServics {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    public Department addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        if (departmentDto.getDoctorId()!=null) {

            Doctor doctor = doctorRepo.findById(departmentDto.getDoctorId()).
                    orElseThrow(()->new RuntimeException("doctor not found"));

            department.setDoctor(doctor);
        }
        return departmentRepo.save(department);
    }

    public Department updateDepartment(DepartmentDto departmentDto, Long departmentId) {
        Department department = departmentRepo.findById(departmentId).
                orElseThrow(()->new RuntimeException("Department not found"));

            if (departmentDto.getName()!=null) {
                department.setName(departmentDto.getName());
            }
            if (departmentDto.getDoctorId()!=null) {
                Doctor doctor =doctorRepo.findById(departmentDto.getDoctorId()).
                        orElseThrow(()->new RuntimeException("Doctor not found")) ;
               department.setDoctor(doctor);
            }
            return departmentRepo.save(department);




    }
    public String  deleteDepartment(Long departmentId) {
        Department department = departmentRepo.findById(departmentId).
                orElseThrow(()->new RuntimeException("Department not found"));

            departmentRepo.delete(department);
            return "Department deleted";

    }


    public Department getDepartmentById(Long departmentId) {
        Department department = departmentRepo.findById(departmentId).
                orElseThrow(()->new RuntimeException("Department not found"));

            return department;

    }
}
