package com.java.projectfinal.model.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UserUpdateRequest(

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "First name is required")
        @Length(min = 3, message = "The first name must have minimum 3 letters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Length(min = 3, message = "The last name must have minimum 3 letters")
        String lastName,
        String salutation,

        @NotBlank(message = "Phone number is required")
        @Length(min = 3, message = "The phone number must have minimum 3 digits")
        String phone,

        @NotBlank(message = "Address is required")
        @Length(min = 3, message = "The address must have minimum 3 letters")
        String address,

        @NotNull(message = "The date of birth cannot be null")
        LocalDate dateOfBirth){
}
