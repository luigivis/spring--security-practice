package org.example.springsecuritypractice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record ClientEditDto(
        @NotBlank(message = "Name can't be null")
        @Size(max = 30, message = "Name contain more than 30 characters")
        String name,

        @NotBlank(message = "last name can't be null")
        @Size(max = 30, message = "last name contain more than 30 characters")
        String lastName,

        @NotBlank(message = "phone can't be null")
        @Pattern(regexp = "^[0-9]+$",
                message = "Phone number isn't valid")
                @Size(max = 8, message = "Phone number length no satisfied" )
        String phone,
        @Email(message = "Email isn't valid")
        String email,
        String address) {}
