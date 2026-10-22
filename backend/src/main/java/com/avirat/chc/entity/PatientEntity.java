package com.avirat.chc.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.naming.Name;
import java.time.LocalDate;

@Entity
@Table(name = "PATIENT_INFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PatientEntity {

    @Id
    @Column(name = "HEALTH_CARD_NUMBER")
    private Integer healthCardNo;

    @Column(name = "username",nullable = false)
    private String userName;

    @Column(name = "DATE_OF_BRITH",nullable = false)
    private LocalDate DOB;

    @Column(name = "AGE_OF_PATIENT")
    private Integer age;

    @Column(name = "WEIGHT_OF_PATIENT")
    private Integer weight;

    @Column(name = "GENDER",nullable = false)
    private String gender;

    @Column(name = "BLOOD_PRESSURE")
    private String BloodPressure;

    @Column(name = "BLOOD_GROUP")
    private String BloodGroup;







}
