package com.avirat.chc.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ThirdPartyConfiguration {

    @Bean
    public ModelMapper getBeanModelMapper(){
        ModelMapper mapper = new ModelMapper();

        // Optional: Define strict matching strategy
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return new ModelMapper();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public OpenAPI getDataForSwagger()
    {
        return new OpenAPI()
                .info(new Info().title("Avirat Academy").
                                contact(new Contact().email("info@avirat.com").name("Sales Person"))
                                .description("This testing Purpose")
                                .summary("My Learning Project ")
                        );
}
}
