package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.AppointmentDto;
import com.Hospital_Managment.demo.Entity.Appointment;
import com.Hospital_Managment.demo.Servics.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentServics;

    @PostMapping("/post")
    public Appointment postAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentServics.postAppointment(appointmentDto);
    }

    @GetMapping("/getbyid/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable Long appointmentId) {
        return appointmentServics.getAppointmentById(appointmentId);

    }
    @GetMapping("/getall")
    public List<Appointment> getAllAppointments( ) {
        return appointmentServics.getAllAppointments();

    }

    @PatchMapping("/patch/{appointmentId}")
    public Appointment patchAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable Long appointmentId) {
        return appointmentServics.patchAppointment(appointmentDto,appointmentId);
    }

    @DeleteMapping("/delete/{appointmentId}")
    public String deleteAppointmentById(@PathVariable Long appointmentId) {
        return appointmentServics.deleteAppointmentById(appointmentId);
    }




}
