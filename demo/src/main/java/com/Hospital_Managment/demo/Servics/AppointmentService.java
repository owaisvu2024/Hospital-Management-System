package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Dto.AppointmentDto;
import com.Hospital_Managment.demo.Entity.Appointment;
import com.Hospital_Managment.demo.Entity.Doctor;
import com.Hospital_Managment.demo.Entity.PatientEntity;
import com.Hospital_Managment.demo.Repository.AppointmentRepo;
import com.Hospital_Managment.demo.Repository.DoctorRepo;
import com.Hospital_Managment.demo.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    public Appointment postAppointment(AppointmentDto appointmentDto) {

        PatientEntity patientEntity = patientRepo.findById(appointmentDto.getPatientId()).
                orElseThrow(()->new RuntimeException("Patient not found"));

        Doctor doctorEntity = doctorRepo.findById(appointmentDto.getDoctorId()).
                orElseThrow(()->new RuntimeException("Doctor not found"));
        Appointment app = new Appointment();

        app.setReason(appointmentDto.getReason());
        app.setStatus(appointmentDto.getStatus());
        app.setPatient(patientEntity);
        app.setDoctor(doctorEntity);

        return appointmentRepo.save(app);

    }

    public Appointment getAppointmentById(Long AppointmentId) {
        Appointment app = appointmentRepo.findById(AppointmentId).
                orElseThrow(()->new RuntimeException("Appointment not found"));

        return app;
    }

    public List<Appointment> getAllAppointments() {

        List<Appointment> app = appointmentRepo.findAll();
        if (app.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }
        return app;
    }


    public Appointment patchAppointment(AppointmentDto appointmentDto, Long AppointmentId) {

        Appointment app = appointmentRepo.findById(AppointmentId).orElseThrow(() ->
                new RuntimeException("Appointment with id " + AppointmentId + " not found"));


             if (appointmentDto.getReason() != null) {
                 app.setReason(appointmentDto.getReason());
             }

             if (appointmentDto.getStatus() != null) {
                 app.setStatus(appointmentDto.getStatus());
             }
             if (appointmentDto.getDoctorId() != null) {
                 Doctor doctor=doctorRepo.findById(appointmentDto.getDoctorId()).
                         orElseThrow(()->new RuntimeException("Doctor with id " + appointmentDto.getDoctorId() + " not found"));
                app.setDoctor(doctor);
             }
             if (appointmentDto.getPatientId() != null) {
                 PatientEntity patientEntity=patientRepo.findById(appointmentDto.getPatientId()).
                         orElseThrow(()->new RuntimeException("Patient with id " + appointmentDto.getPatientId() + " not found"));
                 app.setPatient(patientEntity);
             }

            return   appointmentRepo.save(app);
        }




    public String deleteAppointmentById(Long appointmentId) {
         Appointment app=appointmentRepo.findById(appointmentId).
                orElseThrow(() -> new RuntimeException("Appointment  id  not found"));
        appointmentRepo.delete(app);
        return "Appointment  id deleted";

    }

}
