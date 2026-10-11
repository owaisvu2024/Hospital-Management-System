package com.Hospital_System.demo.Servics;

import com.Hospital_System.demo.Dto.InsuranceDto;
import com.Hospital_System.demo.Dto.InsuranceResponseDto;
import com.Hospital_System.demo.Entity.InsuranceEntity;
import com.Hospital_System.demo.Repository.InsuranceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepo insuranceRepo;

    public InsuranceResponseDto addInsurance(InsuranceDto insuranceDto) {
        InsuranceEntity insurance = new InsuranceEntity();
        insurance.setInsuranceProvider(insuranceDto.getInsuranceProvider());
        insurance.setValidUntil(insuranceDto.getValidUntil());
        insurance.setPolicyNo(insuranceDto.getPolicyNo());

        InsuranceEntity saved = insuranceRepo.save(insurance);
        return convertDto(saved);

    }

    public InsuranceResponseDto updateInsurance(InsuranceDto insuranceDto, Long insuranceId) {
        InsuranceEntity insuranceEntity = insuranceRepo.findById(insuranceId).
                orElseThrow(() -> new UsernameNotFoundException("insurance not found"));
        if (insuranceDto.getInsuranceProvider() != null) {
            insuranceEntity.setInsuranceProvider(insuranceDto.getInsuranceProvider());
        }
        if (insuranceDto.getValidUntil() != null) {
            insuranceEntity.setValidUntil(insuranceDto.getValidUntil());

        }
        if (insuranceDto.getPolicyNo() != null) {
            insuranceEntity.setPolicyNo(insuranceDto.getPolicyNo());
        }
        InsuranceEntity saved = insuranceRepo.save(insuranceEntity);
        return convertDto(saved);

    }

    public List<InsuranceResponseDto> getAllInsurance() {

        List<InsuranceEntity> insuranceEntities = insuranceRepo.findAll();
        List<InsuranceResponseDto> Dto = new ArrayList<>();
        for (InsuranceEntity Entity : insuranceEntities) {
            Dto.add(convertDto(Entity));
        }

        return Dto;
    }

    public InsuranceResponseDto getInsuranceById(Long insuranceId) {
        InsuranceEntity insuranceEntity = insuranceRepo.findById(insuranceId).orElseThrow(() ->
                new UsernameNotFoundException("insurance not found"));
        return convertDto(insuranceEntity);
    }

    public String deleteInsurance(Long insuranceId) {
        InsuranceEntity insuranceEntity = insuranceRepo.findById(insuranceId).
                orElseThrow(() -> new UsernameNotFoundException("insurance not found"));
        insuranceRepo.delete(insuranceEntity);
        return "delete successfully";

    }

    private InsuranceResponseDto convertDto(InsuranceEntity entity) {
        InsuranceResponseDto dto = new InsuranceResponseDto();
        dto.setInsuranceId(entity.getInsuranceId());
        dto.setInsuranceProvider(entity.getInsuranceProvider());
        dto.setValidUntil(entity.getValidUntil());
        dto.setPolicyNo(entity.getPolicyNo());
        dto.setInsuranceDate(entity.getInsuranceDate());
        return dto;

    }
}
