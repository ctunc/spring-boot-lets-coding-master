package com.letscoding.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class HelloApiController {

    @GetMapping
    public String helloApi(){
        return "Hello Api";
    }

}
