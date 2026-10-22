package com.avirat.chc.responnsedto;

import com.avirat.chc.entity.DoseEntity;
import com.avirat.chc.entity.MedicineInFoEntity;
import com.avirat.chc.entity.PatientEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineInFoResponseDTO {

    private  String medicineInfoId;

    private String medicineName;

    private DoseResponseDTO doseEntity;

    private Integer days;

    private String remark;





}
