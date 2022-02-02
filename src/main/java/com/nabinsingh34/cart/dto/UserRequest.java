package com.nabinsingh34.cart.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @NotBlank(message = "Email should not be empty")
    @Size(min = 3,max = 5,message = "size must be between 3 to 5")
    private String firstName;

    @NotBlank(message = "Email should not be empty")
    private  String lastName;

    @NotBlank(message = "Email should not be empty")
    private  String email;

    @NotBlank(message = "Password shoudl not be empty")
    private String password;
}
