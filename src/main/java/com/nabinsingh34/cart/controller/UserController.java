package com.nabinsingh34.cart.controller;


import com.nabinsingh34.cart.dto.LoginRequest;
import com.nabinsingh34.cart.dto.UserRequest;
import com.nabinsingh34.cart.exception.UserNameNotFound;
import com.nabinsingh34.cart.model.User;
import com.nabinsingh34.cart.service.UserService;
import com.nabinsingh34.cart.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

//    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<Object> getAllUser(@RequestParam(name = "email",required = false) String email) {
//        logger.debug();
        if (email != null) {
            Optional<User> user= userService.getUserByEmail(email);
            if(user.isPresent()){

                log.info("User with email {} fetched {}",user.get().getEmail(),user.get().getEmail());
                return ApiResponse.generateResponse(HttpStatus.OK.value(),"User with email: "+email+" fetched successfully",user,null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User with id "+email+" not found in our database",null,"User not Found");

        } else {
            List<User> users = userService.getAllUsers();
            if (users.size() > 0) {
                log.info("{} users avaiable",users.size());
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
//             return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User with id "+id +" not found in our database",null,"User not Found");
        throw new UserNameNotFound("user with id: "+id+" not found.");
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRequest userRequest){
       Optional<User> existingUser=  userService.getUserByEmail(userRequest.getEmail());
             if(existingUser.isPresent()){
                 return ApiResponse.generateResponse(HttpStatus.OK.value(),"User fetched successfully",existingUser,null);
             }
             User user= userService.createUser(userRequest);
             return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User created",user,null);
//
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody @Valid UserRequest userRequest){
        Optional<User> existingUser=userService.getUserById(id);
        if(!existingUser.isPresent()){

        }

        return userService.updateUser(existingUser.get(),userRequest);
    }
//
//    @PostMapping("/users/login")
//    public ResponseEntity<Object> login(@RequestBody UserRequest userRequest){
////      Map<String, Object> userDetails= userService.login(userRequest);
////      return ApiResponse.generateResponse(HttpStatus.OK.value(), "User authenticated",userDetails,null);
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        Optional<User> existingUser=  userService.getUserById(id);
        if(existingUser.isPresent()){
            userService.deleteUser(id);
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"User deleted successfully with id "+id,null,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"User with id "+id +" not found in our database",null,"User not Found");

    }
    @PostMapping("/users/login")
    public ResponseEntity<Object> userLogin(@RequestBody LoginRequest loginRequest){
        Map<String,Object> res= userService.userLogin(loginRequest);
        return ApiResponse.generateResponse(HttpStatus.OK.value(), "Login successfull",res,null);

    }
}
