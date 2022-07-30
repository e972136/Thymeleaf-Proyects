package com.gaspar.web.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "first_name",nullable = false)
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;
}
