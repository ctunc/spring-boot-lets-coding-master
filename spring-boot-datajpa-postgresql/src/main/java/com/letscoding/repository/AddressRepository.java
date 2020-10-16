package com.letscoding.repository;

import com.letscoding.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AddressRepository extends JpaRepository<Adress,Long> {


}
