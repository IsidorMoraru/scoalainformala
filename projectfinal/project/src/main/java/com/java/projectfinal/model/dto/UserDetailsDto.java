package com.java.projectfinal.model.dto;

import com.java.projectfinal.model.entity.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class UserDetailsDto {
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String salutation;

    private String phone;

    private String address;

    private LocalDate dateOfBirth;
    private UserRole role;
}
