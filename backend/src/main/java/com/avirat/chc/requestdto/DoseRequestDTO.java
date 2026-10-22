package com.avirat.chc.requestdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoseRequestDTO {

    private boolean morning;

    private boolean afternoon;

    private boolean night;


}
