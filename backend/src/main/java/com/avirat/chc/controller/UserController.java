package com.avirat.chc.controller;

import com.avirat.chc.helper.Messages;
import com.avirat.chc.requestdto.UserRequestDTO;
import com.avirat.chc.responnsedto.UserResponseDTO;
import com.avirat.chc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
@Tag(name = "User Functionality",description = "To Sign up new user , Login to application , GetPersonal Info of user ")
public class UserController {

    @Autowired
    UserService userService;

    //signUp
    @Operation(description = "To create / signup for Application")
    @PostMapping("/signUpNew")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        String msg = userService.signUp(userRequestDTO);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }



    //login
    @Operation(description = "Login to the Application")
    @GetMapping("/Login")
    public ResponseEntity<UserResponseDTO> login (@RequestParam(name = "UserName") String userName , @RequestParam(name = "Passward")String passward){


        UserResponseDTO userResponseDTO = userService.loginId(userName,passward);
        if(userResponseDTO !=null)
            return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    //get
    @Operation(description = "Get the Personal Information")
    @GetMapping("/GetPersonalInfo/{UserId}")
    public ResponseEntity<UserResponseDTO> getUserData(@PathVariable(name = "UserId") Integer userId){

        UserResponseDTO userResponseDTO = userService.getUserData(userId);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.ACCEPTED);

    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> profile(Authentication authentication) {
        return ResponseEntity.ok(userService.getByUserName(authentication.getName()));
    }

    //Update
    @Operation(description = "Update the Information ")
    @PutMapping ("/updatedata")
    public ResponseEntity<String> update(@PathVariable  Integer userId , @RequestBody  UserRequestDTO userRequestDTO ){
        String msg =  userService.updateData(userId,userRequestDTO);
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }



}
