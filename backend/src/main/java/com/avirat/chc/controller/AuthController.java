package com.avirat.chc.controller;

import com.avirat.chc.jwtStuff.MyJWTOperation;
import com.avirat.chc.requestdto.JWTRequestDTO;
import com.avirat.chc.responnsedto.JWTResponceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Safe")
@Tag(name = "Security Function ")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private MyJWTOperation myJWTOperation;

    private Logger logger =  LoggerFactory.getLogger(AuthController.class);

    @Operation(description = "Create the Token")
    @PostMapping("/login")
    public ResponseEntity<JWTResponceDTO> login(@RequestBody JWTRequestDTO request) {

        this.doAuthenticate(request.getUserName(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.myJWTOperation.generateToken(userDetails);

        JWTResponceDTO response = JWTResponceDTO.builder()
                .token(token)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        authenticationManager.authenticate(authentication);


    }


}
