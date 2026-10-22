package com.avirat.chc.requestdto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDTO {

    private Integer age;

    private Integer weight;

    private String BloodPressure;

    private String BloodGroup;


}
