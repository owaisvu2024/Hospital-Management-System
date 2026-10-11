package com.Hospital_System.demo.Servics;

import com.Hospital_System.demo.Dto.DoctorDto;
import com.Hospital_System.demo.Dto.DoctorResponsDto;
import com.Hospital_System.demo.Entity.DepartmentEntity;
import com.Hospital_System.demo.Entity.DoctorEntity;
import com.Hospital_System.demo.Repository.DepartmentRepo;
import com.Hospital_System.demo.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    public DoctorResponsDto AddDoctor(DoctorDto doctorDto) {

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setDoctorName(doctorDto.getDoctorName());
        doctorEntity.setEmail(doctorDto.getEmail());
        doctorEntity.setShifTiming(doctorDto.getShifTiming());
        doctorEntity.setMaxPatientPerDay(doctorDto.getMaxPatientPerDay());

        doctorEntity.setSpecialization(doctorDto.getSpecialization());
        if (doctorDto.getDepartId() != null) {
            DepartmentEntity departmentEntity = departmentRepo.findById(doctorDto.getDepartId()).
                    orElseThrow(() -> new UsernameNotFoundException("Department Not Found"));
            doctorEntity.setDepartment(departmentEntity);
        }


        DoctorEntity saved = doctorRepo.save(doctorEntity);
        return convertDto(saved);


    }

    public DoctorResponsDto UpdateDoctor(DoctorDto doctorDto, Long doctorId) {

        DoctorEntity doctorEntity = doctorRepo.findById(doctorId).orElseThrow(() ->
                new UsernameNotFoundException("Doctor Not Found"));

        if (doctorDto.getDoctorName() != null) {
            doctorEntity.setDoctorName(doctorDto.getDoctorName());
        }
        if (doctorDto.getDepartId() != null) {
            DepartmentEntity doctorDepartment = departmentRepo.findById(doctorDto.getDepartId()).
                    orElseThrow(() -> new UsernameNotFoundException("department not found"));
            doctorEntity.setDepartment(doctorDepartment);
        }
        if (doctorDto.getEmail() != null) {
            doctorEntity.setEmail(doctorDto.getEmail());
        }
        if (doctorDto.getSpecialization() != null) {
            doctorEntity.setSpecialization(doctorDto.getSpecialization());
        }
        return convertDto(doctorEntity);


    }

    public String DeleteDoctor(Long doctorId) {
        DoctorEntity doctorEntity = doctorRepo.findById(doctorId).
                orElseThrow(() -> new UsernameNotFoundException("Doctor Not Found"));
        doctorRepo.delete(doctorEntity);
        return "Doctor Deleted Successfully";
    }

    public DoctorResponsDto GetDoctorById(Long doctorId) {
        DoctorEntity doctorEntity = doctorRepo.findById(doctorId).
                orElseThrow(() -> new UsernameNotFoundException("Doctor Not Found"));
        return convertDto(doctorEntity);
    }

    public Page<DoctorResponsDto> GetAllDoctors(Pageable pageable) {


        return doctorRepo.findAll(pageable).map(entity -> convertDto(entity)) ;
    }


    private DoctorResponsDto convertDto(DoctorEntity entity) {
        DoctorResponsDto dto = new DoctorResponsDto();


        dto.setDoctorId(entity.getDoctorId());
        dto.setDoctorName(entity.getDoctorName());
        dto.setEmail(entity.getEmail());
        dto.setShifTiming(entity.getShifTiming());
        if (entity.getDepartment() != null) {
            dto.setDepartId(entity.getDepartment().getDepartId());
        }
        dto.setMaxPatientPerDay(entity.getMaxPatientPerDay());
        dto.setSpecialization(entity.getSpecialization());

        return dto;

    }
}
