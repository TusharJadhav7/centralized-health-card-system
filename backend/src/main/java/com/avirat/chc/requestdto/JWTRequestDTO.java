package com.avirat.chc.requestdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTRequestDTO {

    private String userName;

    private String password;
}
