package com.letscoding.controller;

import com.letscoding.annotations.JsonController;
import com.letscoding.dto.UserDto;
import com.letscoding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("{withDozer}")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto,@PathVariable(name = "withDozer") boolean withDozer){
        return withDozer?ResponseEntity.ok(userService.save(userDto)):
                ResponseEntity.ok(userService.saveWithoutDozerMapping(userDto));
    }

    @GetMapping("{withDozer}")
    public ResponseEntity<List<UserDto>> listAllUsers(@PathVariable(name = "withDozer") boolean withDozer){
        return  withDozer?ResponseEntity.ok(userService.getAll()):ResponseEntity.ok(userService.getAllWithoutDozerMapping());
    }

}
