package com.nabinsingh34.cart.dto;

import lombok.Data;

@Data
public class ClientResponse {
    private Object data;
    private String message;
    private String errors;
    private Integer statusCode;

}
