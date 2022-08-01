package com.gaspar.web.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty(message = "Falta nombre")
    @Column(name = "first_name",nullable = false)
    String firstName;

    @NotEmpty(message = "Falta apellido")
    @Column(name = "last_name")
    String lastName;

    @Email
    @Column(name = "email")
    String email;
}
