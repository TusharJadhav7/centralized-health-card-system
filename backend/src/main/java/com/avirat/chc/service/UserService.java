package com.avirat.chc.service;

import com.avirat.chc.requestdto.UserRequestDTO;
import com.avirat.chc.responnsedto.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    //signUp --> DTO Are coverted into Entity

    public String signUp(UserRequestDTO userRequestDTO);


    //logIn  --> User valid

    public UserResponseDTO loginId(String userName,String password);


    // Update -->DTO to Entity

     public String updateData(Integer userId,UserRequestDTO userRequestDTO);


    //get  -->  Entity to DTO

    public UserResponseDTO getUserData(Integer userId);

    public UserResponseDTO getByUserName(String userName);

    
}
