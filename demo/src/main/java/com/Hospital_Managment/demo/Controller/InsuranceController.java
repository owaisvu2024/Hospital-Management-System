package com.Hospital_Managment.demo.Controller;

import com.Hospital_Managment.demo.Dto.InsuranceDto;
import com.Hospital_Managment.demo.Entity.Insurance;
import com.Hospital_Managment.demo.Servics.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hospital")

public class InsuranceController {
@Autowired
    private InsuranceService insuranceServise;
    @PostMapping("/add")
    public Insurance addInsuranse(@Valid @RequestBody InsuranceDto insuranseDto){
        return insuranceServise.addInsurance(insuranseDto);
    }

    @GetMapping("/get/{insuranceId}")
    public Insurance getInsuranse(@PathVariable Long insuranceId){
        return insuranceServise.getInsurance(insuranceId);

    }

    @DeleteMapping ("/delete/{insuranceId}")
    public String deleteInsuranse(@PathVariable Long insuranceId){
        return insuranceServise.deleteInsurance(insuranceId);

    }

    @PatchMapping("/patch/{insuranceId}")
    public Insurance patchInsuranse(@RequestBody InsuranceDto insuranseDto , @PathVariable Long insuranceId){

        return insuranceServise.patchInsurance(insuranseDto,insuranceId);
    }

    @GetMapping("/getAll")
    public List<Insurance> getAll(){
        return insuranceServise.getAll();

    }



}
