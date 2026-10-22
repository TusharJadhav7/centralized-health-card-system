package com.avirat.chc.service;

import com.avirat.chc.globalexception.DataNotFoundException;
import com.avirat.chc.requestdto.MedicalRecordRequestDTO;
import com.avirat.chc.responnsedto.MedicalRecordForSinglePatientDTO;
import com.avirat.chc.responnsedto.MedicalRecordResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface MedicalRecordService {

    //User And Doctor Valid Ahe ki Nahi
    public Boolean validUserAndDoctor(String userName, Integer healthCardNo, Long doctorRegNo );

    public String createNewMedicalRecord(MedicalRecordRequestDTO medicalRecordRequestDTO,Integer healthCardNo);

    public Boolean validatePatient (String userName , Integer healthCardNo) throws DataNotFoundException;

    public MedicalRecordResponseDTO getMedicalRecord (Integer healthCardNo);

    public MedicalRecordForSinglePatientDTO getAllMedicalRecord(Integer healthCardNo);




}
