package com.Hospital_System.demo.Servics;

import com.Hospital_System.demo.Dto.PatientDto;
import com.Hospital_System.demo.Dto.PatientResponseDto;
import com.Hospital_System.demo.Entity.InsuranceEntity;
import com.Hospital_System.demo.Entity.PatientEntity;
import com.Hospital_System.demo.Repository.InsuranceRepo;
import com.Hospital_System.demo.Repository.PatientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private InsuranceRepo insuranceRepo;

    public PatientResponseDto addPatient(PatientDto patientDto) {

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(patientDto.getName());
        if (patientDto.getInsuranceId() != null) {
            InsuranceEntity insuranceEntity = insuranceRepo.findById(patientDto.getInsuranceId()).
                    orElseThrow(() -> new UsernameNotFoundException("Insurance id not found"));
            patientEntity.setInsuranceEntity(insuranceEntity);
        }

        patientEntity.setGender(patientDto.getGender());
        patientEntity.setEmail(patientDto.getEmail());
        patientEntity.setBloodType(patientDto.getBloodType());
        patientEntity.setDateOfBirth(patientDto.getDateOfBirth());
        PatientEntity saved=patientRepo.save(patientEntity);
        return convertDto(patientEntity);

    }

    public PatientResponseDto updatePatient(PatientDto patientDto, Long patientId) {

        PatientEntity patientEntity = patientRepo.findById(patientId).
                orElseThrow(() -> new UsernameNotFoundException("Patient Not Found"));

        if (patientDto.getName() != null) {
            patientEntity.setName(patientDto.getName());
        }
        if (patientDto.getGender() != null) {
            patientEntity.setGender(patientDto.getGender());
        }
        if (patientDto.getInsuranceId() != null) {
            InsuranceEntity insuranceEntity = insuranceRepo.findById(patientDto.getInsuranceId()).
                    orElseThrow(() -> new UsernameNotFoundException("insurance not found"));
            patientEntity.setInsuranceEntity(insuranceEntity);
        }
        if (patientDto.getEmail() != null) {
            patientEntity.setEmail(patientDto.getEmail());
        }
        if (patientDto.getBloodType() != null) {
            patientEntity.setBloodType(patientDto.getBloodType());
        }
        if (patientDto.getDateOfBirth() != null) {
            patientEntity.setDateOfBirth(patientDto.getDateOfBirth());
        }
        PatientEntity saved = patientRepo.save(patientEntity);
        return convertDto(saved);

    }

    public String deletePatient(Long patientId) {
        PatientEntity patientEntity = patientRepo.findById(patientId).
                orElseThrow(() -> new UsernameNotFoundException("Patient Not Found"));

        patientRepo.deleteById(patientId);
        return "Delete Patient Successfully";
    }

    public PatientResponseDto getPatientById(Long patientId) {
        PatientEntity patientEntity = patientRepo.findById(patientId).
                orElseThrow(() -> new UsernameNotFoundException("Patient Not Found"));

        return convertDto(patientEntity);
    }

    public Page<PatientResponseDto> getAllPatients(Pageable pageable) {



        Page<PatientEntity> entityPage= patientRepo.findAll(pageable);
        return entityPage.map(entity -> convertDto(entity));
    }

    private PatientResponseDto convertDto(PatientEntity entity) {
        PatientResponseDto dto = new PatientResponseDto();

        dto.setPatientId(entity.getPatientId());
        dto.setName(entity.getName());
        dto.setGender(entity.getGender());
        dto.setEmail(entity.getEmail());
        dto.setBloodType(entity.getBloodType());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setCreateAt(entity.getCreateAt());
        if (entity.getInsuranceEntity() != null) {
            dto.setInsuranceId(entity.getInsuranceEntity().getInsuranceId());
        }
        return dto;

    }

}
