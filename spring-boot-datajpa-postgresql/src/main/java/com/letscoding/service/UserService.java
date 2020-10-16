package com.letscoding.service;

import com.letscoding.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service
public interface UserService {

    UserDto save(UserDto userDto);

    void delete(Long id);

    List<UserDto> getAll();

    Page<UserDto> getAll(Pageable pageable);

    UserDto saveWithoutDozerMapping(UserDto userDto);

    List<UserDto> getAllWithoutDozerMapping();

}
