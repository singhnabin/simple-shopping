package com.nabinsingh34.cart.service;

import com.nabinsingh34.cart.dto.LoginRequest;
import com.nabinsingh34.cart.dto.UserRequest;
import com.nabinsingh34.cart.model.User;
import com.nabinsingh34.cart.repository.UserRepository;
import com.nabinsingh34.cart.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
@Slf4j
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CartUserDetailsService cartUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User createUser(UserRequest userRequest) {
        User newUser= new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setPassword(encodePassword(userRequest.getPassword()));
       return userRepository.save(newUser);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user, UserRequest userRequest) {

        user.setLastName(userRequest.getLastName());
        user.setFirstName(userRequest.getFirstName());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Map<String,Object> userLogin(LoginRequest loginRequest) {
        Map<String,Object> tokenMap= new HashMap<>();
        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    }catch (AuthenticationException e) {

            throw new BadCredentialsException("Invalid credentials");
        }
        UserDetails userDetails= cartUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        String  token= jwtUtils.generateToken(userDetails);
        tokenMap.put("jwt",token);
        tokenMap.put("user",userDetails.getUsername());
        tokenMap.put("roles",userDetails.getAuthorities());

       log.info("Generated token is: {}",token);
       return tokenMap;

        }

//    public Map<String, Object> login(UserRequest userRequest) {
//        Map<String,Object> authenticateUser= new HashMap<>();
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
//        } catch (AuthenticationException e){
//
//        throw new BadCredentialsException("Invalid credentials");
//
//        }
//        UserDetails userDetails= myUserDetailsService.loadUserByUsername(userRequest.getEmail());
//        String token= jwtUtils.generateToken(userDetails);
//        authenticateUser.put("token",token);
//        authenticateUser.put("user",userDetails.getUsername());
//        authenticateUser.put("role",userDetails.getAuthorities());
//
//      return authenticateUser;
//    }
}
