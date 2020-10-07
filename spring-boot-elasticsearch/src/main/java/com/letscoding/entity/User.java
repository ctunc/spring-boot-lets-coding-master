package com.letscoding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Document(indexName = "users",type = "user")
@Getter
@Setter
@NoArgsConstructor  //constructor parameter zero
@AllArgsConstructor //all field set with constructor
public class User {

    @Id
    private String id;

    @Field(name="name",type = FieldType.Text)
    private String name;

    @Field(name="lastName",type = FieldType.Text)
    private String lastName;

    @Field(name="adress",type = FieldType.Text)
    private String adress;

    @Field(type = FieldType.Date,format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date birthDate;

}
