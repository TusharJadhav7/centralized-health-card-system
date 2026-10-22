package com.avirat.chc.service.impl;

import com.avirat.chc.entity.MedicalRecordEntity;
import com.avirat.chc.entity.PatientEntity;
import com.avirat.chc.entity.UserEntity;
import com.avirat.chc.globalexception.DataNotFoundException;
import com.avirat.chc.repo.MedicalRecordRepo;
import com.avirat.chc.repo.PatientRepo;
import com.avirat.chc.repo.UserRepo;
import com.avirat.chc.requestdto.MedicalRecordRequestDTO;
import com.avirat.chc.responnsedto.MedicalRecordForSinglePatientDTO;
import com.avirat.chc.responnsedto.MedicalRecordResponseDTO;
import com.avirat.chc.responnsedto.PatientResponseDTO;
import com.avirat.chc.service.MedicalRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataNotFoundAction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    MedicalRecordRepo medicalRecordRepo;



    @Override
    public Boolean validUserAndDoctor(String userName, Integer healthCardNo, Long doctorRegNo) {

        UserEntity UserData = userRepo.getUserNameDataCheck(userName,healthCardNo).orElse(null);

        UserEntity DoctorData = userRepo.findBydoctorRegNo(doctorRegNo).orElse(null);
        if(UserData!=null && DoctorData!=null)
            return true;
        else
            return false;


    }

    @Override
    public String createNewMedicalRecord(MedicalRecordRequestDTO medicalRecordRequestDTO, Integer healthCardNo) {

        //DTO--> ENTITY -->> full -->> save

          MedicalRecordEntity medicalRecordEntity = modelMapper.map(medicalRecordRequestDTO, MedicalRecordEntity.class);

        // entity cha full karava lage

        // Create date -->> Date , local date
        String createdate = LocalDate.now().toString();
        medicalRecordEntity.setCreateDate(createdate);

        // paatient cha object
        UserEntity userEntity =  userRepo.findByHealthCardNo(healthCardNo).orElseThrow(()->new DataNotFoundException(("User is not find by HealthCardNo"+healthCardNo)));

         PatientEntity patientEntity = patientRepo.findByHealthCardNo(healthCardNo).orElse(null);

        //patient cha data medicalrecord mathe ghetala
         // PatientEntity patientData = medicalRecordEntity.getPatientEntity();
          // Patient Data Save karava lagel

        if(patientEntity==null) {
            //medical
            patientEntity = new PatientEntity();
            patientEntity.setUserName(userEntity.getUsername());
            patientEntity.setHealthCardNo(userEntity.getHealthCardNo());
            patientEntity.setDOB(userEntity.getDob());
            patientEntity.setGender(userEntity.getGender());

           patientEntity =  patientRepo.save(patientEntity);
        }

        // set back into medical record
        //Attach patient to medical record

        medicalRecordEntity.setPatientEntity(patientEntity);

        medicalRecordRepo.save(medicalRecordEntity);


        return "Data insert complete";
    }

    @Override
    public Boolean validatePatient(String userName, Integer healthCardNo) throws DataNotFoundException {

        Supplier<DataNotFoundException> supplierException= ()-> new DataNotFoundException("Patient is not found");
        UserEntity userData =  userRepo.getUserNameDataCheck(userName , healthCardNo).orElseThrow(() -> new DataNotFoundException(supplierException.toString()));
        return true;
    }

    //Double Patient Record Are Display.
    @Override
    public MedicalRecordResponseDTO getMedicalRecord(Integer healthCardNo) {

        PatientEntity patientEntity=patientRepo.findById(healthCardNo).orElseThrow(()->new RuntimeException("Patient is not found."));

        List<MedicalRecordEntity> medicalRecordEntityList=medicalRecordRepo.findByPatientEntity(patientEntity).orElseThrow(()->new RuntimeException("No medical records found."));

        List<MedicalRecordResponseDTO> medicalRecordResponseDTOList=medicalRecordEntityList.stream()
                .map(a->modelMapper
                        .map(a,MedicalRecordResponseDTO.class)).toList();

        return (MedicalRecordResponseDTO) medicalRecordResponseDTOList;

    }

    @Override
    public MedicalRecordForSinglePatientDTO getAllMedicalRecord(Integer healthCardNo) {

        PatientEntity patientEntity = patientRepo.findById(healthCardNo).orElseThrow(()->new DataNotFoundException("Patient is not found by HealthCardNo."+healthCardNo));
        // patientEntity la PatientResponseDTO mathe convert it.
        PatientResponseDTO patientResponseDTO = modelMapper.map(patientEntity , PatientResponseDTO.class);

        List<MedicalRecordEntity> medicalRecordEntityList = medicalRecordRepo.findByPatientEntity(patientEntity).orElseThrow(()->new DataNotFoundException("No medical records found."));
        // Convert all entity object into the ResDto and Collect as list
        List<MedicalRecordResponseDTO> medicalRecordResponseDTOLis  = medicalRecordEntityList.stream()
                .map(a -> modelMapper.map(a,MedicalRecordResponseDTO.class)).toList();

        List<MedicalRecordResponseDTO> medicalRecordResponseDTOList=null;
        MedicalRecordForSinglePatientDTO medicalRecordForSinglePatientDTO = MedicalRecordForSinglePatientDTO.builder()
                .patientResponseDTO(patientResponseDTO)
                .medicalRecordResponseDTOList(medicalRecordResponseDTOList)
                .build();

        return medicalRecordForSinglePatientDTO;
    }
}
