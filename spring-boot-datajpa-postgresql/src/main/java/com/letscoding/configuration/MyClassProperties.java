package com.letscoding.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties
@Data
@Component
public class MyClassProperties {
    private Integer size;
    private String name;
    private String description;
    private List<String> servers;
}
