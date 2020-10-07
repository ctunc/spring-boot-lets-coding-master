package com.letscoding.controller;


import com.letscoding.entity.User;
import com.letscoding.repository.UserRepository;
import com.letscoding.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user =new User();
        user.setId("0001");
        user.setName("ctunc");
        user.setLastName("tunc");
        user.setAdress("istanbul");
        user.setBirthDate(Calendar.getInstance().getTime());
        userRepository.save(user);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<User>> getUser(@PathVariable String search){
        List<User> users= userRepository.getByCustomQuery(search);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{name}/{lastName}")
    public ResponseEntity<List<User>> getUserInfo(@PathVariable String name,@PathVariable String lastName){
        List<User> users= userRepository.findByNameLikeOrLastNameLike(name,lastName);
        return ResponseEntity.ok(users);
    }

}
