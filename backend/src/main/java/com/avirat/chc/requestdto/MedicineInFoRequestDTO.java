package com.avirat.chc.requestdto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineInFoRequestDTO {

    @NonNull
    @Size(max = 200,min = 10)
    private String medicineName;

    private DoseRequestDTO doseEntity;

    @NonNull
    @Size(max = 200,min = 10)
    private Integer days;

    @NonNull
    @Size(max = 200,min = 10)
    private String remark;


}
