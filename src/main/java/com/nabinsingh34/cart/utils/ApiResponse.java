package com.nabinsingh34.cart.utils;


import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
public static ResponseEntity<Object> generateResponse(Integer status, String message, Object data, Object errors){
    Map<String,Object> newMap=new HashMap<>();
    newMap.put("statusCode",status);
    newMap.put("message",message);
    newMap.put("data",data);
    newMap.put("errors",errors);

    return ResponseEntity.ok().body(newMap);

}
}
