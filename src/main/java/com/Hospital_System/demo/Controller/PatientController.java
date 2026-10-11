package com.Hospital_System.demo.Controller;

import com.Hospital_System.demo.Dto.PatientDto;
import com.Hospital_System.demo.Dto.PatientResponseDto;
import com.Hospital_System.demo.Servics.PatientService;
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
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','PATIENT')")
    @PostMapping("/post")
    public ResponseEntity<PatientResponseDto> addPatient(@Valid @RequestBody PatientDto patientDto) {
   PatientResponseDto patient=patientService.addPatient(patientDto);
        return new  ResponseEntity<>(patient, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPTIONIST','PATIENT')")
    @PatchMapping("/patch/{patientId}")
    public ResponseEntity<PatientResponseDto> updatePatient(@RequestBody PatientDto patientDto,@PathVariable Long patientId) {

        return  ResponseEntity.ok(patientService.updatePatient(patientDto,patientId)) ;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.deletePatient(patientId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPTIONIST','DOCTOR','PATIENT')")
    @GetMapping("/get/{patientId}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPTIONIST')")
    @GetMapping("/getall")
    public ResponseEntity<Page<PatientResponseDto>> getAllPatients(
            @RequestParam(defaultValue = "0") int Page,
            @RequestParam(defaultValue = "5")  int Size
    ) {

        Pageable pageable = PageRequest.of(Page, Size, Sort.by("patientId").descending());
        return  ResponseEntity.ok(patientService.getAllPatients(pageable));
    }
}
