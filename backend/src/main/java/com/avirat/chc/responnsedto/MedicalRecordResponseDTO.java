package com.avirat.chc.responnsedto;

import com.avirat.chc.entity.MedicineInFoEntity;
import com.avirat.chc.entity.PatientEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordResponseDTO {

    private String medicalRecordId;

    private String createDate;

    private  Long doctorRegNo;

    private List<MedicineInFoResponseDTO> medicineInFoResponseDTO;


}
