package com.avirat.chc.authenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticatiponEntryPoint implements AuthenticationEntryPoint {

//Spring Security calls your custom AuthenticationEntryPoint
//is used to handle unauthorized access attempts in a Spring Security application
//AuthenticationEntryPoint is an interface in Spring Security used to handle unauthenticated access to protected resources

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter writer =response.getWriter();
        writer.println("Access Denied !!  "+ authException.getMessage());

    }
}
