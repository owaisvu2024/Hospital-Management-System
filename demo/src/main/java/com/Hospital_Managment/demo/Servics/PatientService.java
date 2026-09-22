package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Dto.PatientDto;
import com.Hospital_Managment.demo.Entity.Insurance;
import com.Hospital_Managment.demo.Entity.PatientEntity;
import com.Hospital_Managment.demo.Repository.InsuranceRepo;
import com.Hospital_Managment.demo.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private InsuranceRepo insuranceRepo;

    public PatientEntity addPatient(PatientDto patientDto) {

        if (patientRepo.existsByEmail(patientDto.getEmail())){
            throw new RuntimeException("Patient email already exists");
        }
         PatientEntity patientEntity = new PatientEntity();

         patientEntity.setName(patientDto.getName());
        patientEntity.setEmail(patientDto.getEmail());
        patientEntity.setBirthDate(patientDto.getBirthDate());
        patientEntity.setBloodGroup(patientDto.getBloodGroup());
        patientEntity.setGender(patientDto.getGender());

        if (patientDto.getInsuranceId()!=null) {
            Insurance insurance = insuranceRepo.findById(patientDto.getInsuranceId()).orElseThrow(()->
                    new RuntimeException("Insurance not found"));
            patientEntity.setInsurance(insurance);

        }


        return patientRepo.save(patientEntity);

    }

    public PatientEntity patchPatient(PatientDto patientDto,Long patientId) {

        PatientEntity patient = patientRepo.findById(patientId).
                orElseThrow(()->new RuntimeException("Patient not found"));


            if (patientDto.getName() != null) {
                patient.setName(patientDto.getName());
            }
            if (patientDto.getEmail() != null) {
                patient.setEmail(patientDto.getEmail());
            }
            if (patientDto.getBirthDate() != null) {
                patient.setBirthDate(patientDto.getBirthDate());
            }
            if (patientDto.getBloodGroup() != null) {
                patient.setBloodGroup(patientDto.getBloodGroup());
            }
        if (patientDto.getGender() != null) {
            patient.setGender(patientDto.getGender());
        }
        if (patientDto.getInsuranceId() != null) {
            Insurance insurance=insuranceRepo.findById(patientDto.getInsuranceId()).
                    orElseThrow(()->new RuntimeException("Insurance not found"));
            patient.setInsurance(insurance);
        }



            return patientRepo.save(patient);

        }






    public String deletePatient(Long patientId) {
        PatientEntity patientEntity = patientRepo.findById(patientId).
                orElseThrow(()->new RuntimeException("Patient not found"));

        patientRepo.delete(patientEntity);
        return "Patient has been deleted";

    }

    public PatientEntity getPatient( Long patientId) {
        PatientEntity patientEntity=patientRepo.findById(patientId).
                orElseThrow(()->new RuntimeException("Patient not found"));


            return patientEntity;


    }
}

