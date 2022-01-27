package com.nabinsingh34.cart.exception;

public class UserNameNotFound extends RuntimeException{
    private String message;

    public UserNameNotFound(String message) {
        super(message);
        this.message = message;
        }



}
