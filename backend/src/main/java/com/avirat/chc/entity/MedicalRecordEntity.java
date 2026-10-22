package com.avirat.chc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MEDICAL_RECORD_OF_PATIENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordEntity {

    @Id
    @Column(name = "RECORD_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String medicalRecordId;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "DOCTOR_REG_NO")
    private  Long doctorRegNo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MedicineInFoEntity> medicineInFoEntity;

    @ManyToOne(cascade = CascadeType.DETACH)
    private PatientEntity patientEntity;

}
