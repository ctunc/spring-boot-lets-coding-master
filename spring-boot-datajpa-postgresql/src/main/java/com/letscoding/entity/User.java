package com.letscoding.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class User {

    @Id
    @SequenceGenerator(name = "seq_user", allocationSize = 1)
    @GeneratedValue(generator = "seq_user",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name",length = 100)
    private String name;

    @Column(name = "lastname",length = 100)
    private String lastname;

    @OneToMany
    @JoinColumn(name="user_address_id")
    private List<Adress> userAddress;



}
