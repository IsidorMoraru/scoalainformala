package com.java.projectfinal.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String salutation;

    @Column
    private String phone;

    @Column
    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
