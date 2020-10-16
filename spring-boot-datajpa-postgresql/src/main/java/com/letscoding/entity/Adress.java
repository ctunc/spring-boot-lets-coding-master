package com.letscoding.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_adresses")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@ToString
public class Adress {

    @Id
    @SequenceGenerator(name = "seq_user_adres", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_adres", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 500, name = "adress")
    private String adress;

    @Column(name = "adress_type",length = 5)
    private String adressType;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "user_adres_id")
    private User user;
}
