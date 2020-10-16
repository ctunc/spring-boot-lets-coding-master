package com.letscoding.service.impl;

import com.letscoding.annotations.TestProfile;
import com.letscoding.dto.UserDto;
import com.letscoding.entity.Adress;
import com.letscoding.entity.User;
import com.letscoding.repository.AddressRepository;
import com.letscoding.repository.UserRepository;
import com.letscoding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/*
* Try to find mapping enum column for dozer mapping configuration
* */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    private final AddressRepository addressRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User userEntity = new User();
        mapper.map(userDto,userEntity);
        userEntity = userRepository.save(userEntity);

        UserDto saverUser=new UserDto();
        mapper.map(userEntity,saverUser);

        List<Adress> liste = new ArrayList<>();
        User finalUserEntity = userEntity;
        userDto.getUserAddress().forEach(item -> {
            Adress adres = new Adress();
            adres.setAdress(item);
            adres.setAdressType("1");
            adres.setActive(true);
            adres.setUser(finalUserEntity);
            liste.add(adres);
        });
        addressRepository.saveAll(liste);
        saverUser.setId(finalUserEntity.getId());
        return saverUser;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserDto> getAll() {
        List<User> userEntities =userRepository.findAll();
        List<UserDto> userDtos=new ArrayList<>();
        userEntities.forEach(item->{
            UserDto userDto=new UserDto();
            mapper.map(item,userDto);
                    userDto.setUserAddress(
                            (item.getUserAddress() != null) ?
                                    item.getUserAddress().stream().map(Adress::getAdress).collect(Collectors.toList())
                                    : null);
            userDtos.add(userDto);
                }
                );

        return userDtos;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public UserDto saveWithoutDozerMapping(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        final  User userDb = userRepository.save(user);

        List<Adress> liste = new ArrayList<>();
        userDto.getUserAddress().forEach(item -> {
            Adress adress = new Adress();
            adress.setAdress(item);
            adress.setAdressType("1");
            adress.setActive(true);
            adress.setUser(userDb);
            liste.add(adress);
        });
        addressRepository.saveAll(liste);
        userDto.setId(userDb.getId());
        return userDto;
    }

    @Override
    public List<UserDto> getAllWithoutDozerMapping() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(it -> {
            UserDto userDto =new UserDto();
            userDto.setId(it.getId());
            userDto.setName(it.getName());
            userDto.setLastname(it.getLastname());
            userDto.setUserAddress(
                    it.getUserAddress() != null ?
                            it.getUserAddress().stream().map(Adress::getAdress).collect(Collectors.toList())
                            : null);
            userDtos.add(userDto);
        });
        return userDtos;
    }

}
