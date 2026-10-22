package com.avirat.chc.controller;

import com.avirat.chc.helper.Messages;
import com.avirat.chc.requestdto.MedicalRecordRequestDTO;
import com.avirat.chc.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/medicalRecord")
@Tag(name = "Doctor Functionality")
public class DoctorController {

@Autowired
    MedicalRecordService medicalRecordService;

public boolean validDataUserAndDoctor(@RequestParam String userName , @RequestParam Integer healthCardNo, @RequestParam Long doctorRegNo) {
    Boolean medicalData = medicalRecordService.validUserAndDoctor(userName, healthCardNo, doctorRegNo);

    if (medicalData!=null)
        return true;
    else
        return false;


}


    @Operation(description = "Create Medicine Record ")
    @PostMapping("/createMedicineRecord")
    public ResponseEntity<Messages> createMedicalRecord(
            @RequestParam String userName,
            @RequestParam Integer healthCardNo,
            @RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO){

        String returnMsg;
        Boolean validated= medicalRecordService.validUserAndDoctor(userName,healthCardNo, medicalRecordRequestDTO.getDoctorRegNo());
        Messages messages;
        if(validated){
            returnMsg = medicalRecordService.createNewMedicalRecord(medicalRecordRequestDTO,healthCardNo);
            messages=Messages.builder()
                    .msg(returnMsg)
                    .build();
            return new ResponseEntity<>(messages, HttpStatus.CREATED);
        }

        else{
            returnMsg="Patient or Doctor is not valid.";
            messages=Messages.builder()
                    .msg(returnMsg)
                    .build();
            return new ResponseEntity<>(messages, HttpStatus.NOT_FOUND);


        }
        }
}
