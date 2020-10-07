package com.letscoding.repository;

import com.letscoding.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Adress,Long> {


}
