package com.avirat.chc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DOSE_OF_MEDICINE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer doseId;

    //Morning
    @Column(name = "MORNING_DOSE",nullable = false)
    private boolean morning;

    //Afternoon
    @Column(name = "AFTERNOON_DOSE",nullable = false)
    private boolean afternoon;

    //Night
    @Column(name = "NIGHT_DOSE",nullable = false)
    private boolean night;
}
