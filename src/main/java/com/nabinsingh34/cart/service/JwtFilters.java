//package com.nabinsingh34.cart.service;
//
//import com.nabinsingh34.cart.utils.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Service
//public class JwtFilters extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader= httpServletRequest.getHeader("Authorization");
//        String token=null;
//        String username= null;
//        if(authHeader!=null && authHeader.startsWith("Bearer")){
//            token= authHeader.substring(7);
//            username= jwtUtils.extractUsername(token);
//        }
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//           UserDetails userDetails= myUserDetailsService.loadUserByUsername(username);
//           if (jwtUtils.validateToken(token,userDetails)){
//               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//           }
//        }
//
//
//                filterChain.doFilter(httpServletRequest, httpServletResponse);
//
//
//
//
//
//    }
//}
