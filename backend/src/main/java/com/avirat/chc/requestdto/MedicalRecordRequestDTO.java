package com.avirat.chc.requestdto;

import com.avirat.chc.entity.MedicineInFoEntity;
import com.avirat.chc.entity.PatientEntity;
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
public class MedicalRecordRequestDTO {


    private  Long doctorRegNo;

    private List<MedicineInFoRequestDTO> medicineInFoEntity;

    private PatientRequestDTO patientEntity;
}
