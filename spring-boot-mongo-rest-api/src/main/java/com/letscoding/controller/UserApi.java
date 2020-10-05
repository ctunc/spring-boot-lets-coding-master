package com.letscoding.controller;

import com.letscoding.entity.User;
import com.letscoding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user =new User();
        user.setName("letscoading");
        user.setLastName("spring-boot");
        userRepository.save(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody  User user){
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }


}
