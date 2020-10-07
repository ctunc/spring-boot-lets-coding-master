package com.letscoding.service;

import com.letscoding.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    void delete(Long id);

    List<UserDto> getAll();

    Page<UserDto> getAll(Pageable pageable);

}
