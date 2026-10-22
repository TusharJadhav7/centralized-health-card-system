package com.avirat.chc.controller;

import com.avirat.chc.responnsedto.MedicalRecordForSinglePatientDTO;
import com.avirat.chc.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/All")
@Tag(name = "Common Application for All")
public class CommonFunctionOfBoth {

    @Autowired
    MedicalRecordService medicalRecordService;

    @Operation(description = "Patient Data")
    @GetMapping("/PatientData")
    public MedicalRecordForSinglePatientDTO getAllMedicalRecord(@RequestParam (name = "healthCardNo" ) Integer healthCardNo ) {

        MedicalRecordForSinglePatientDTO medicalRecordForSinglePatientDTO = medicalRecordService.getAllMedicalRecord(healthCardNo);
        return medicalRecordForSinglePatientDTO;


    }
}
