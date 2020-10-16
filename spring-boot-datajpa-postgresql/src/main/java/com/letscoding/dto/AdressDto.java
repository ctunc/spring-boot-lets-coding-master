package com.letscoding.dto;


import com.letscoding.entity.Adress;
import com.letscoding.entity.User;
import lombok.*;
import org.dozer.Mapping;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class AdressDto implements Serializable {
    @Mapping("id")
    private Long id;

    private String adress;

    private String adressType;

    private Boolean active;

    private User user;

}
