package com.avirat.chc.responnsedto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordForSinglePatientDTO {

    // Patient DATA => patient
    public PatientResponseDTO patientResponseDTO;
    // LIST Medical Records
    public List<MedicalRecordResponseDTO> medicalRecordResponseDTOList;

}
