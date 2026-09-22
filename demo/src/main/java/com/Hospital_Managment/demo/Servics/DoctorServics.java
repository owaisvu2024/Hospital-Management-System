package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Dto.DoctorDto;
import com.Hospital_Managment.demo.Entity.Doctor;
import com.Hospital_Managment.demo.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServics {
    @Autowired
    private DoctorRepo doctorRepo;

    public Doctor addDoctor(DoctorDto doctorDto) {

       if (doctorRepo.existsByDoctorEmail(doctorDto.getDoctorEmail())){
           throw new RuntimeException("Doctor Email Already Exists");}
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorDto.getDoctorName());
        doctor.setDoctorEmail(doctorDto.getDoctorEmail());
        doctor.setDoctorPhone(doctorDto.getDoctorPhone());
        doctor.setSpecialization(doctorDto.getSpecialization());


        return doctorRepo.save(doctor);
    }

    public Doctor patchDoctor(DoctorDto doctorDto,Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).
                orElseThrow(()->new RuntimeException("Doctor Id not found"));

            if (doctorDto.getDoctorName()!=null) {
                doctor.setDoctorName(doctorDto.getDoctorName());
            }
            if (doctorDto.getDoctorEmail()!=null) {
                doctor.setDoctorEmail(doctorDto.getDoctorEmail());
            }
            if (doctorDto.getDoctorPhone()!=null) {
                doctor.setDoctorPhone(doctorDto.getDoctorPhone());
            }
            if (doctorDto.getSpecialization()!=null) {
                doctor.setSpecialization(doctorDto.getSpecialization());
            }
             return doctorRepo.save(doctor);

        }


    public Doctor getDoctor(Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).
                orElseThrow(()->new RuntimeException("Doctor Id not found"));

            return doctor;

    }
    public String deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).
                orElseThrow(()->new RuntimeException("Doctor Id not found"));

        doctorRepo.delete(doctor);
        return "Doctor with id " + doctorId + " deleted";

    }
    public List<Doctor> getAllDoctor() {
        List<Doctor> doctor = doctorRepo.findAll();
        if (doctor.isEmpty()) {
            throw  new RuntimeException("Doctor not found in database");
        }
        return doctor;
    }
}
