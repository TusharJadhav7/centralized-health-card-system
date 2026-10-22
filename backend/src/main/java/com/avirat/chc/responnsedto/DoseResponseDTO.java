package com.avirat.chc.responnsedto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoseResponseDTO {

    private Integer doseId;

    //Morning
    private boolean morning;

    //Afternoon
    private boolean afternoon;

    //Night
    private boolean night;
}
