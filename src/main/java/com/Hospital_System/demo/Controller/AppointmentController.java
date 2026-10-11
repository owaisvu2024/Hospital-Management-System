package com.Hospital_System.demo.Controller;

import com.Hospital_System.demo.Dto.AppointmentDto;
import com.Hospital_System.demo.Dto.AppointmentResponseDto;
import com.Hospital_System.demo.Servics.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','DOCTOR')")
    @GetMapping("/getId/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long appointmentId) {

       return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
    }



    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','PATIENT')")
    @PostMapping ("/post")
    public ResponseEntity<AppointmentResponseDto>addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        AppointmentResponseDto appointment= appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','DOCTOR','PATIENT')")
    @PatchMapping ("/patch/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable Long appointmentId) {

        return ResponseEntity.ok(appointmentService.updateAppointment(appointmentDto, appointmentId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST')")
    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(appointmentId));

    }

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST')")
    @GetMapping("/getall")
    public ResponseEntity<Page<AppointmentResponseDto>>allAppointment(
            @RequestParam(defaultValue = "0") int Page,
            @RequestParam(defaultValue = "5") int Size
    ) {
        Pageable pageable = PageRequest.of(Page, Size, Sort.by("appointmentId").descending());
        return ResponseEntity.ok(appointmentService.allAppointment(pageable));
    }

}
