package com.avirat.chc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Integer userId;

    //userName
    @Column(name = "USER_NAME",nullable = false,unique = true)
    private String userName;

    //fridt name
    @Column(name = "FIRST_NAME")
    private String firstName;

    // last name
    @Column(name = "LAST_NAME")
    private String lastName;

    //password
    @Column(name = "USER_PASSWORD",nullable = false,unique = true)
    private String password;

    //email
    @Column(name = "USER_EMAIL",nullable = false)
    private String email;

    //contact Number
    @Column(name = "USER_CONTACT_NO",nullable = false)
    private Long contact;

    //role
    @Column(name = "USER_ROle",nullable = false)
    private String role;

    //health cardNo
    @Column(name = "USER_HEALTH_CARD_NO",nullable = false)
    private Integer healthCardNo;

    //doctorId
    @Column(name = "DOCTOR_REG_NO",nullable = false)
    private Long doctorRegNo;

    //chemistID
    @Column(name = "CHEMIST_REG_NO",nullable = false)
    private Long chemistRegNo;

    //dob
    @Column(name = "USER_DATE_OF_BRITH",nullable = false)
    private LocalDate dob;

    // Address
    @Column(name = "USER_ADDRESS",nullable = false)
    private String address;

    // Gender
    @Column(name = "GRNDER",nullable = false)
    private String gender;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(this.getRole()));

        return  simpleGrantedAuthorityList ;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
