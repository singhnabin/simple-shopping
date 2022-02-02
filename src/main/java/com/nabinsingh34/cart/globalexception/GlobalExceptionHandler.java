package com.nabinsingh34.cart.globalexception;

import com.nabinsingh34.cart.exception.UserNameNotFound;
import com.nabinsingh34.cart.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> errorList= new ArrayList<>();
       List<ObjectError> errors= ex.getBindingResult().getAllErrors();
       for(ObjectError err:errors){
           errorList.add(err.getDefaultMessage());
           log.info("errors is: {}"+err);
       }
       return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Field errors",null,errorList);

    }
    @ExceptionHandler({UserNameNotFound.class,IllegalAccessException.class})
    public ResponseEntity<Object> handUserException(Exception ex){
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Username exception found",null,ex.getMessage());
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadException(Exception ex){
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Authentication failed",null,ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleSignatureException(Exception ex){
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST.value(),"Bad request",null,ex.getMessage());
    }

}
