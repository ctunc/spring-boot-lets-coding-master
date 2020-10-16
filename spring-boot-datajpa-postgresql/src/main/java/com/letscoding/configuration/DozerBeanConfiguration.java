package com.letscoding.configuration;


import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerBeanConfiguration {

    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }

}
