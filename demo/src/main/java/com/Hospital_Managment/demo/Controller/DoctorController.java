package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.DoctorDto;
import com.Hospital_Managment.demo.Entity.Doctor;
import com.Hospital_Managment.demo.Servics.DoctorServics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorServics doctorservics;

    @PostMapping("/post")
    public Doctor addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return doctorservics.addDoctor(doctorDto);
    }

    @GetMapping("/getbyid/{doctorId}")
    public Doctor getDoctor(@PathVariable Long doctorId) {
        return doctorservics.getDoctor(doctorId);

    }

    @GetMapping("/getall")
    public List<Doctor> getAllDoctor() {
        return doctorservics.getAllDoctor();
    }

    @PatchMapping("/patch/{doctorId}")
    public Doctor patchDoctor(@RequestBody DoctorDto doctorDto,@PathVariable Long doctorId) {
        return doctorservics.patchDoctor(doctorDto,doctorId);

    }
 @DeleteMapping("/delete/{doctorId}")
    public String deleteDoctor(@PathVariable Long doctorId) {
        return doctorservics.deleteDoctor(doctorId);
 }

}
