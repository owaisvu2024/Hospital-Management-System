package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.DepartmentDto;
import com.Hospital_Managment.demo.Entity.Department;
import com.Hospital_Managment.demo.Servics.DepartmentServics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentServics departmentServics;

    @PostMapping("/post")
    public Department addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        return departmentServics.addDepartment(departmentDto);
    }

    @GetMapping("/getbyid/{departmentId}")
    public Department getDepartmentById(@PathVariable Long departmentId) {
        return departmentServics.getDepartmentById(departmentId);

    }

    @PatchMapping("/patch/{departmentId}")
    public Department updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable Long departmentId) {

        return departmentServics.updateDepartment(departmentDto, departmentId);
    }

    @DeleteMapping("/delete/{departmentId}")
    public String deleteDepartment(@PathVariable Long departmentId) {
        return departmentServics.deleteDepartment(departmentId);
    }



}
