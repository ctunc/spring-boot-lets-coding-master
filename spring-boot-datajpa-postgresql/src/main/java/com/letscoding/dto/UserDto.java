package com.letscoding.dto;

import com.letscoding.entity.Adress;
import lombok.*;
import org.dozer.Mapping;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class UserDto implements Serializable {

    @Mapping("id")
    private Long id;

    private String name;

    private String lastname;

    private List<String> userAddress;

}
