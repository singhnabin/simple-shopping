package com.nabinsingh34.cart.service;

import com.nabinsingh34.cart.dto.UserRequest;
import com.nabinsingh34.cart.model.User;
import com.nabinsingh34.cart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User createUser(UserRequest userRequest) {
        User newUser= new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
       return userRepository.save(newUser);
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
}
