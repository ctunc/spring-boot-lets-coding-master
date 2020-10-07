package com.letscoding.service.impl;

import com.letscoding.dto.UserDto;
import com.letscoding.entity.Adress;
import com.letscoding.entity.User;
import com.letscoding.repository.AddressRepository;
import com.letscoding.repository.UserRepository;
import com.letscoding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    @Override
    public UserDto save(UserDto userDto) {
        User user =new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        User userDb=userRepository.save(user);

        List<Adress> addressList=new ArrayList<>();

        userDto.getUserAddress().forEach(item->{
            Adress adress=new Adress();
            adress.setActive(true);
            adress.setAddress(item);
            adress.setAdressType(Adress.AddressType.HOME_ADDRESS);
            adress.setUser(userDb);
            addressList.add(adress);
        });
        userDto.setId(userDb.getId());
        return userDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserDto> getAll() {
        List<User> user =userRepository.findAll();
        List<UserDto> userDtos=new ArrayList<>();

        user.forEach(item->{
            UserDto userDto=new UserDto();
            userDto.setName(item.getName());
            userDto.setLastname(item.getLastname());
            userDto.setId(item.getId());
            userDto.setUserAddress(item.getUserAddress().stream().map(Adress::getAddress).collect(Collectors.toList())
            );
            userDtos.add(userDto);
                }
                );

        return userDtos;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return null;
    }
}
