package com.nabinsingh34.cart.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "Email should not be empty")
    private  String email;

    @NotBlank(message = "Password shoudl not be empty")
    private String password;
}
