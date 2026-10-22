package com.avirat.chc.responnsedto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDTO {

    private Integer healthCardNo;

    private String userName;

    private LocalDate DOB;

    private Integer age;

    private Integer weight;

    private String gender;

    private String BloodPressure;

    private String BloodGroup;



}
