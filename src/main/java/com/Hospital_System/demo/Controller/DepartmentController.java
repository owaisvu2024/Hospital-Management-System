package com.Hospital_System.demo.Controller;

import com.Hospital_System.demo.Dto.DepartmentDto;
import com.Hospital_System.demo.Dto.DepartmentResponseDto;
import com.Hospital_System.demo.Entity.DepartmentEntity;
import com.Hospital_System.demo.Servics.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depart")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getall")
    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','DOCTOR')")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {

        return  ResponseEntity.ok(departmentService.getAllDepartments());
    }


    @GetMapping("/getid/{departId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','RECEPTIONIST','DOCTOR')")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long departId) {
       return ResponseEntity.ok(departmentService.getDepartmentById(departId));
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DepartmentResponseDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
         DepartmentResponseDto departmentEntity = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(departmentEntity, HttpStatus.CREATED) ;
    }

    @PatchMapping("/patch/{departId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable Long departId) {

        return ResponseEntity.ok(departmentService.updateDepartment(departmentDto, departId));
    }

    @DeleteMapping("/delete/{departId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String>  deleteDepartment(@PathVariable Long departId) {
        return  ResponseEntity.ok(departmentService.deleteDepartment(departId));
    }
}
