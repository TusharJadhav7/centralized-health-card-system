package com.avirat.chc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEDICINE_INFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineInFoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MEDICINE_INFO_ID")
    private  String medicineInfoId;

    @Column(name = "MEDICINE_NAME",nullable = false,length = 200)
    private String medicineName;

    @OneToOne(cascade = CascadeType.ALL)
    private DoseEntity doseEntity;

    @Column(name = "DAYS",nullable = false)
    private Integer days;

    @Column(name = "REMARK",nullable = false,length = 500)
    private String remark;


}
