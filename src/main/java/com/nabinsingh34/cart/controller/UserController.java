package com.nabinsingh34.cart.controller;


import com.nabinsingh34.cart.dto.UserRequest;
import com.nabinsingh34.cart.model.User;
import com.nabinsingh34.cart.service.UserService;
import com.nabinsingh34.cart.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<Object> getAllUser(@RequestParam(name = "email",required = false) String email) {
        if (email != null) {
            Optional<User> user= userService.getUserByEmail(email);
            if(user.isPresent()){
                return ApiResponse.generateResponse(HttpStatus.OK.value(),"User with email: "+email+" fetched successfully",user,null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User with id "+email+" not found in our database",null,"User not Found");

        } else {
            List<User> users = userService.getAllUsers();
            if (users.size() > 0) {
                return ApiResponse.generateResponse(HttpStatus.OK.value(), users.size() + " users available", users, null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "No users available", null, "Users not Found");
        }
    }




    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable  Long id){
             Optional<User> existingUser=  userService.getUserById(id);
             if(existingUser.isPresent()){
                 return ApiResponse.generateResponse(HttpStatus.OK.value(),"User fetched successfully",existingUser,null);
             }
             return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User with id "+id +" not found in our database",null,"User not Found");
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserRequest userRequest){
        User savedUser=userService.createUser(userRequest);
        return savedUser;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){
        Optional<User> existingUser=userService.getUserById(id);
        if(!existingUser.isPresent()){

        }

        return userService.updateUser(existingUser.get(),userRequest);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        Optional<User> existingUser=  userService.getUserById(id);
        if(existingUser.isPresent()){
            userService.deleteUser(id);
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"User deleted successfully with id "+id,null,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User with id "+id +" not found in our database",null,"User not Found");

    }
}
