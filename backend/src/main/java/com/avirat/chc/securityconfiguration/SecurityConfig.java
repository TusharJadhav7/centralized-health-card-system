package com.avirat.chc.securityconfiguration;



import com.avirat.chc.authenticationEntryPoint.JwtAuthenticatiponEntryPoint;
import com.avirat.chc.ownfilter.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticatiponEntryPoint jwtAuthenticatiponEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    // Allow requests from deployed Vercel frontend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
                "https://centralized-health-card-system.vercel.app"
        ));

        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        configuration.setAllowedHeaders(Arrays.asList("*"));

        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain myguard(HttpSecurity httpSecurity)
            throws Exception {

        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(cs -> cs.disable())

                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**",

                                "/user/signUpNew",
                                "/user/Login",
                                "/user/GetPersonalInfo/**",
                                "/user/updatedata",

                                "/Safe/login",
                                "/All/PatientData"
                        ).permitAll()

                        .requestMatchers(
                                "/medicalRecord/createMedicineRecord"
                        ).hasRole("DOCTOR")

                        .requestMatchers("/user/profile").authenticated()

                        .anyRequest().authenticated()
                )

                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(jwtAuthenticatiponEntryPoint)
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return httpSecurity.build();
    }
}





//import com.avirat.chc.authenticationEntryPoint.JwtAuthenticatiponEntryPoint;
//import com.avirat.chc.ownfilter.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SecurityConfig {
//
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    private JwtAuthenticatiponEntryPoint jwtAuthenticatiponEntryPoint;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public SecurityFilterChain myguard(HttpSecurity httpSecurity)  throws  Exception
//    {
//        httpSecurity
//                .csrf(cs-> cs.disable())
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers(
//                                        "/v3/api-docs",
//                                        "/v3/api-docs/**",
//                                        "/swagger-ui.html",
//                                        "/swagger-ui/**",
//                                        "/swagger-resources/**",
//                                        "/webjars/**",
//                                        "/user/signUpNew",
//                                        "/user/Login",
//                                        "/user/GetPersonalInfo",
//                                        "/user/updatedata",
//                                        "/Safe/login",
//                                        "/All/PatientData"
//                                ).permitAll()
//                                .requestMatchers("/user/signUpNew",
//                                        "/user/Login",
//                                        "/user/GetPersonalInfo",
//                                        "/user/updatedata").permitAll()
//                                .requestMatchers("/Safe/login").permitAll()
//                                .requestMatchers("/All/PatientData").permitAll()
//                                .requestMatchers("/medicalRecord/createMedicineRecord").hasRole("DOCTOR")
//                                .requestMatchers("/user/profile").authenticated()
//                                .anyRequest().authenticated()
//
//                )
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticatiponEntryPoint))
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return httpSecurity.build();
//
//
//
//
//   }
//
//}