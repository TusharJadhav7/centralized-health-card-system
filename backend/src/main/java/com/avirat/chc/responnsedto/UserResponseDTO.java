package com.avirat.chc.responnsedto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Integer userId;

    //userName
    private String userName;

    //fridt name
    private String firstName;

    // last name
    private String lastName;

    //password
    private String password;

    //email
    private String email;

    //contact Number
    private Long contact;

    //role
    private String role;

    //health cardNo
    private Integer healthCardNo;

    //doctorId
    private Long doctorRegNo;

    //chemistID
    private Long chemistRegNo;

    //dob
    private LocalDate dob;

    // Address
    private String address;

    // Gender
    private String gender;


}
