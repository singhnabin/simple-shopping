package com.nabinsingh34.cart.exception;

public class AuthenticationFailed extends Throwable {
    private String message;
    public AuthenticationFailed(String invalid_token) {
        super(invalid_token);
        this.message = invalid_token;
    }
}
