package com.Hospital_System.demo.Controller;

import com.Hospital_System.demo.Dto.InsuranceDto;
import com.Hospital_System.demo.Dto.InsuranceResponseDto;
import com.Hospital_System.demo.Entity.InsuranceEntity;
import com.Hospital_System.demo.Servics.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<InsuranceResponseDto> addInsurance(@Valid @RequestBody InsuranceDto insuranceDto) {
        InsuranceResponseDto insuranceEntity = insuranceService.addInsurance(insuranceDto);
       return  new ResponseEntity<>(insuranceEntity, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/patch/{insuranceId}")
    public ResponseEntity<InsuranceResponseDto> updateInsurance(@RequestBody InsuranceDto insuranceDto,@PathVariable Long insuranceId) {
       return ResponseEntity.ok(insuranceService.updateInsurance(insuranceDto,insuranceId));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPTIONIST','PATIENT')")
    @GetMapping("/All")
    public ResponseEntity<List<InsuranceResponseDto>> getAllInsurance() {

        return ResponseEntity.ok(insuranceService.getAllInsurance());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','DOCTOR','PATIENT')")
    @GetMapping("/get/{insuranceId}")
    public ResponseEntity<InsuranceResponseDto> getInsuranceById(@PathVariable Long insuranceId) {
       return ResponseEntity.ok(insuranceService.getInsuranceById(insuranceId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping ("/delete/{insuranceId}")
    public ResponseEntity<String>  deleteInsurance(@PathVariable Long insuranceId) {
        return  ResponseEntity.ok(insuranceService.deleteInsurance(insuranceId));
    }

}
