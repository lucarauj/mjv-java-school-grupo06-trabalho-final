package com.jobby.g6.infra.config;

import org.modelmapper.ModelMapper;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
public class modelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        return modelMapper;
    }
}