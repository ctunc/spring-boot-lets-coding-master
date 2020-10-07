package com.letscoding.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String lastname;

    private List<String> userAddress;

}
