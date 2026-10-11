package com.Hospital_System.demo.Servics;

import com.Hospital_System.demo.Dto.DepartmentDto;
import com.Hospital_System.demo.Dto.DepartmentResponseDto;
import com.Hospital_System.demo.Entity.DepartmentEntity;
import com.Hospital_System.demo.Entity.DoctorEntity;
import com.Hospital_System.demo.Repository.DepartmentRepo;
import com.Hospital_System.demo.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    public List<DepartmentResponseDto> getAllDepartments() {
        List<DepartmentEntity> departments = departmentRepo.findAll();
        List<DepartmentResponseDto> Dto = new ArrayList<>();
        for (DepartmentEntity department : departments) {
            Dto.add(convertDto(department));
        }

        return Dto;
    }

    public DepartmentResponseDto getDepartmentById(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepo.findById(departmentId).orElseThrow(() ->
                new UsernameNotFoundException("Department not found"));
        return convertDto(departmentEntity);
    }

    public DepartmentResponseDto addDepartment(DepartmentDto departmentDto) {


        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartName(departmentDto.getDepartName());
        if (departmentDto.getDoctorId() != null) {
            DoctorEntity doctorEntity = doctorRepo.findById(departmentDto.getDoctorId())
                    .orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
            departmentEntity.setDoctor(doctorEntity);
        }

        DepartmentEntity saved = departmentRepo.save(departmentEntity);
        return convertDto(saved);
    }

    public DepartmentResponseDto updateDepartment(DepartmentDto departmentDto, Long departmentId) {


        DepartmentEntity departmentEntity = departmentRepo.findById(departmentId).
                orElseThrow(() -> new UsernameNotFoundException("Department not found"));
        if (departmentDto.getDepartName() != null) {
            departmentEntity.setDepartName(departmentDto.getDepartName());
        }
        if (departmentDto.getDoctorId() != null) {
            DoctorEntity doctorEntity = doctorRepo.findById(departmentDto.getDoctorId()).
                    orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
            departmentEntity.setDoctor(doctorEntity);

        }
        DepartmentEntity saved = departmentRepo.save(departmentEntity);
        return convertDto(saved);
    }

    public String deleteDepartment(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepo.findById(departmentId).
                orElseThrow(() -> new UsernameNotFoundException("Department not found"));

        departmentRepo.deleteById(departmentId);
        return "department deleted successfully";
    }

    private DepartmentResponseDto convertDto(DepartmentEntity entity) {

        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setDepartId(entity.getDepartId());
        dto.setDepartName(entity.getDepartName());
        if (entity.getDoctor() != null) {
            dto.setDoctorId(entity.getDoctor().getDoctorId());
            dto.setDoctorName(entity.getDoctor().getDoctorName());
        }
        Long count = doctorRepo.countByDepartment(entity);
        dto.setCountDoctor(count);
        dto.setCreateAt(entity.getCreateAt());

        return dto;
    }

}
