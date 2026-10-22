package com.avirat.chc.requestdto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    @NotNull(message = "user name cannot be null")
    @Size(max = 20,min = 2,message = "Size is between 10 to 200")
    //userName
    private String userName;

    //fridt name
    @NotNull
    @Size(max = 20,min = 2,message = "UserName Is Present in 5 to 200")
    private String firstName;

    // last name
    @NotNull(message = "Last Name is not null")
    @NotBlank
    @NotEmpty
    @Size(max = 20,min = 2,message = "Size is between 5 to 20")
    private String lastName;

    //password
    @NotNull(message = "password is not null")
    @Size(min = 8,message = "Enter at list 8 Digit ")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$",message = "Password is not valid")
    private String password;

    //email
    @NonNull
    @Size(max = 50,min = 5,message = "Enter Valid Email ID")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Enter Valid Email ID")
    private String email;

    //contact Number
    @NotNull(message = "password is not null")
    @Size(min = 10,message = "Enter at list 10 Digit ")
    private String contact;

    //role
    @NotNull(message = "role name cannot be null")
    @Size(max = 20,min = 2,message = "Size is between 10 to 200")
    private String role;

    //health cardNo
    @NotNull(message = "Health Card Number  cannot be null")
    @Digits(integer = 10, fraction = 0,message = "Size is between 10 to 200")
    private Integer healthCardNo;

    //doctorId
    @NotNull(message = " DoctorReg No cannot be null")
    @Digits(integer = 10, fraction = 0,message = "Size is between 10 to 200")
    private Long doctorRegNo;

    //chemistID
    @NotNull(message = "Chemidt ReNumber  cannot be null")
    @Digits(integer = 10, fraction = 0,message = "Size is between 10 to 200")
    private Long chemistRegNo;

    //dob
    @NotNull(message = "DateFofBrith  cannot be null")
    @PastOrPresent(message = "Date of birth must be in the past or today")

    private LocalDate dob;

    // Address
    @NotNull(message = "Address  cannot be null")
    @Size(max = 200,min = 2,message = "Size is between 10 to 200")
    private String address;

    // Gender
    @NotNull(message = "Gender  cannot be null")
    @Size(max = 10,min = 2,message = "Size is between 2 to 5")
    private String gender;


}
