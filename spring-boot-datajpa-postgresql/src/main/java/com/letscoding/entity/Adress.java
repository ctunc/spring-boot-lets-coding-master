package com.letscoding.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Adress implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user_address", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_address",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 250, name = "address")
    private String address;

    @Column(name="active")
    private Boolean active;

    @Enumerated
    private AddressType adressType;

    @ManyToOne
    @JoinColumn(name="user_address_id")
    private User user;


    public enum AddressType{
        HOME_ADDRESS,
        WORK_ADDRESS,
        OTHER
    }


}
