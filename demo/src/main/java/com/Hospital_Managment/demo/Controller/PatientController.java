package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.PatientDto;
import com.Hospital_Managment.demo.Entity.PatientEntity;
import com.Hospital_Managment.demo.Servics.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientServise;

    @GetMapping("/get/{patientId}")
    public PatientEntity getPatient(@PathVariable Long patientId) {

        return  patientServise.getPatient(patientId);

    }

    @PostMapping("/post")
    public  PatientEntity addPatient(@Valid @RequestBody PatientDto patientDto) {
        return  patientServise.addPatient(patientDto);
    }

    @DeleteMapping("/delete/{patientId}")
    public String deletePatient(@PathVariable Long patientId) {

        return patientServise.deletePatient(patientId);
    }

    @PatchMapping("/patch/{patientId}")
    public PatientEntity patchPatient(@PathVariable Long patientId, @RequestBody PatientDto patientDto) {

        return patientServise.patchPatient(patientDto,patientId);
    }

}
