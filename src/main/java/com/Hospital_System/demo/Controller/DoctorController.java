package com.Hospital_System.demo.Controller;

import com.Hospital_System.demo.Dto.DoctorDto;
import com.Hospital_System.demo.Dto.DoctorResponsDto;
import com.Hospital_System.demo.Entity.DoctorEntity;
import com.Hospital_System.demo.Servics.DoctorService;
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

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping  ("/post")
    public ResponseEntity<DoctorResponsDto> AddDoctor(@Valid @RequestBody DoctorDto doctorDto){
        DoctorResponsDto doctorEntity = doctorService.AddDoctor(doctorDto);
        return new ResponseEntity<>(doctorEntity, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/patch/{doctorId}")
    public ResponseEntity<DoctorResponsDto> UpdateDoctor(@RequestBody DoctorDto doctorDto,@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.UpdateDoctor(doctorDto,doctorId));

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping ("/delete/{doctorId}")
    public ResponseEntity<String>  DeleteDoctor(@PathVariable Long doctorId){

        return ResponseEntity.ok(doctorService.DeleteDoctor(doctorId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','DOCTOR','PATIENT')")
    @GetMapping("/getId/{doctorId}")
    public ResponseEntity<DoctorResponsDto> GetDoctorById(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.GetDoctorById(doctorId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','PATIENT')")
    @GetMapping("/getall")
    public ResponseEntity<Page<DoctorResponsDto>> GetAllDoctors(
            @RequestParam(defaultValue = "0") int Page,
             @RequestParam(defaultValue = "5") int Size

    ){

        Pageable pageable = PageRequest.of(Page, Size, Sort.by("doctorId").descending());
        return ResponseEntity.ok(doctorService.GetAllDoctors(pageable));
    }






}
