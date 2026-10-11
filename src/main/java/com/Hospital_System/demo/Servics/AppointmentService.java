package com.Hospital_System.demo.Servics;

import com.Hospital_System.demo.Dto.AppointmentDto;
import com.Hospital_System.demo.Dto.AppointmentResponseDto;
import com.Hospital_System.demo.Entity.AppointmentEntity;
import com.Hospital_System.demo.Entity.DoctorEntity;
import com.Hospital_System.demo.Entity.PatientEntity;
import com.Hospital_System.demo.Repository.AppointmentRepo;
import com.Hospital_System.demo.Repository.DoctorRepo;
import com.Hospital_System.demo.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;

    public AppointmentResponseDto getAppointmentById(Long appointmentId) {
        AppointmentEntity appointmentEntity = appointmentRepo.findById(appointmentId).
                orElseThrow(() -> new UsernameNotFoundException("appointment not found"));

        return convertDto(appointmentEntity);
    }



    public AppointmentResponseDto addAppointment(AppointmentDto appointmentDto) {

        DoctorEntity doctorEntity = doctorRepo.findById(appointmentDto.getDoctorId()).
                orElseThrow(() -> new UsernameNotFoundException("doctor not found"));

        PatientEntity patientEntity = patientRepo.findById(appointmentDto.getPatientId()).
                orElseThrow(() -> new UsernameNotFoundException("patient not found"));

        LocalDate currentDate = LocalDate.now();

        int currentBooking = appointmentRepo.countByDoctorAndPatientAppointmentDate(doctorEntity, currentDate);

        if (currentBooking >= doctorEntity.getMaxPatientPerDay()) {
            throw new RuntimeException("max patient per day exceeded");
        }
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setStatus(appointmentDto.getStatus());
        appointmentEntity.setDoctor(doctorEntity);
        appointmentEntity.setPatient(patientEntity);

        AppointmentEntity saved = appointmentRepo.save(appointmentEntity);
        return convertDto(saved);

    }

    public AppointmentResponseDto updateAppointment(AppointmentDto appointmentDto, Long appointmentId) {


        AppointmentEntity appointmentEntity = appointmentRepo.findById(appointmentId).
                orElseThrow(() -> new UsernameNotFoundException("appointment not found"));

        if (appointmentDto.getStatus() != null) {
            appointmentEntity.setStatus(appointmentDto.getStatus());
        }
        if (appointmentDto.getDoctorId() != null) {
            DoctorEntity doctorEntity = doctorRepo.findById(appointmentDto.getDoctorId()).
                    orElseThrow(() -> new UsernameNotFoundException("doctor not found"));
            appointmentEntity.setDoctor(doctorEntity);
        }
        if (appointmentDto.getPatientId() != null) {
            PatientEntity patientEntity = patientRepo.findById(appointmentDto.getPatientId())
                    .orElseThrow(() -> new UsernameNotFoundException("patient not found"));
            appointmentEntity.setPatient(patientEntity);
        }
        AppointmentEntity saved = appointmentRepo.save(appointmentEntity);
        return convertDto(saved);
    }

    public String deleteAppointment(Long appointmentId) {
        AppointmentEntity appointmentEntity = appointmentRepo.findById(appointmentId).
                orElseThrow(() -> new UsernameNotFoundException("appointment not found"));

        appointmentRepo.deleteById(appointmentId);
        return "appointment deleted successfully";
    }

    public Page<AppointmentResponseDto> allAppointment(Pageable pageable) {
        Page<AppointmentEntity> entityPage = appointmentRepo.findAll(pageable);


        return entityPage.map(entity -> convertDto(entity));

    }

    private AppointmentResponseDto convertDto(AppointmentEntity entity) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setAppointmentId(entity.getAppointmentId());
        dto.setStatus(entity.getStatus());
        dto.setAppointmentDate(entity.getAppointmentDate());

        dto.setDoctorId(entity.getDoctor().getDoctorId());
        dto.setDoctorName(entity.getDoctor().getDoctorName());
        dto.setPatientId(entity.getPatient().getPatientId());
        dto.setPatientName(entity.getPatient().getName());
        return dto;


    }
}



