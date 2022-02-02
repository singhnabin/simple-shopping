//package com.nabinsingh34.cart.service;
//
//import com.nabinsingh34.cart.model.User;
//import com.nabinsingh34.cart.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Collections;
//
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user= userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with: " + email + " not found"));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),true,true,true,true,getAuthorities("USER"));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        return Collections.singleton(new SimpleGrantedAuthority(role));
//    }
//
//
//}
