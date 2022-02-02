package com.nabinsingh34.cart.controller;

import com.nabinsingh34.cart.dto.ClientResponse;
import com.nabinsingh34.cart.dto.OrderRequest;
import com.nabinsingh34.cart.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/orders")
    public ResponseEntity<Object> getAllOrders(){
        ResponseEntity<ClientResponse> responseEntity= restTemplate.getForEntity("http://localhost:8081/api/orders",ClientResponse.class);
        if(responseEntity.getStatusCode()== HttpStatus.OK){
            ClientResponse clientResponse= responseEntity.getBody();
            Map<String,Object> resMap= new HashMap<>();
            resMap.put("orders",clientResponse.getData());
            return ApiResponse.generateResponse(responseEntity.getStatusCode().value(),clientResponse.getMessage(),resMap,clientResponse.getErrors());

        }


        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Request Failed",null,"Something went wrong");

    }
    @PostMapping("/orders")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest){

        ResponseEntity<ClientResponse> responseEntity= restTemplate.postForEntity("http://localhost:8081/api/orders",orderRequest,ClientResponse.class);
        if(responseEntity.getStatusCode()== HttpStatus.OK){
            ClientResponse body= responseEntity.getBody();
            Map<String,Object> resMap= new HashMap<>();
            resMap.put("order",body.getData());
//            JSONObject res= new JSONObject(body);
//            Object data=res.get("data");
//            log.info("order created: {}",body);
//            log.info("order created: {}",data);
            return ApiResponse.generateResponse(body.getStatusCode(),body.getMessage(),resMap,body.getErrors());
        }

return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Order failed",null,"Something went wrong");
    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable Long id,@RequestBody OrderRequest orderRequest){
        HttpEntity<OrderRequest> requestEntity = new HttpEntity<OrderRequest>(orderRequest, null);
//        Map<String,Long> mapId=new HashMap<>();
//        mapId.put("id",id);

        ResponseEntity<ClientResponse> responseEntity= restTemplate.exchange("http://localhost:8081/api/orders/"+id, HttpMethod.PUT,requestEntity,ClientResponse.class);
        if(responseEntity.getStatusCode()== HttpStatus.OK){
            ClientResponse body= responseEntity.getBody();
            Map<String,Object> resMap= new HashMap<>();
            resMap.put("order",body.getData());
//            JSONObject res= new JSONObject(body);
//            Object data=res.get("data");
//            log.info("order created: {}",body);
//            log.info("order created: {}",data);
            return ApiResponse.generateResponse(body.getStatusCode(),body.getMessage(),resMap,body.getErrors());
        }

        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Order failed",null,"Something went wrong");
    }


}
