package com.Hospital_Managment.demo.Servics;

import com.Hospital_Managment.demo.Dto.InsuranceDto;
import com.Hospital_Managment.demo.Entity.Insurance;
import com.Hospital_Managment.demo.Repository.InsuranceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepo insuranceRepo;

    public Insurance addInsurance(InsuranceDto insuranceDto) {
        Insurance insurance = new Insurance();
        insurance.setPolicyNumber(insuranceDto.getPolicyNumber());
        insurance.setProvider(insuranceDto.getProvider());
        insurance.setValidUntil(insuranceDto.getValidUntil());
        return insuranceRepo.save(insurance);

    }

    public Insurance getInsurance(Long insuranceId) {

        Insurance insurance = insuranceRepo.findById(insuranceId).
                orElseThrow(()->new RuntimeException("insurance not found"));


        return insurance;
    }

    public String deleteInsurance(Long insuranceId) {
        Insurance insurance = insuranceRepo.findById(insuranceId).
                orElseThrow(()->new RuntimeException("insurance not found"));

         insuranceRepo.delete(insurance);
        return "Delete successfully";
    }


public Insurance patchInsurance(InsuranceDto insuranceDto, Long insuranceId) {


    Insurance insurance = insuranceRepo.findById(insuranceId).
            orElseThrow(()->new RuntimeException("insurance not found"));


        if (insuranceDto.getPolicyNumber() != null) {
            insurance.setPolicyNumber(insuranceDto.getPolicyNumber());
        }
        if (insuranceDto.getProvider() != null) {
            insurance.setProvider(insuranceDto.getProvider());
        }

        if (insuranceDto.getValidUntil() != null) {
            insurance.setValidUntil(insuranceDto.getValidUntil());
        }

        return insuranceRepo.save(insurance);
    }




public List<Insurance> getAll(){
       List<Insurance> list = insuranceRepo.findAll();
        if (list.isEmpty() ) {
            throw new RuntimeException("Insurance not found");
        }
        return list;
}
}