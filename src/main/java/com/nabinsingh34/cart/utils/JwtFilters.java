package com.nabinsingh34.cart.utils;

import com.nabinsingh34.cart.service.CartUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilters extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CartUserDetailsService cartUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token= null;
       // "Authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWJpbjEyMzQiLCJleHAiOjE2NDM4MDU4MDcsImlhdCI6MTY0Mzc2OTgwN30.n3GnDtpId0MdSc-WXADo20-dBJ8a10pvExXhBGVSJc"
        String username=null;
        String authHeaders=httpServletRequest.getHeader("Authorization");
        if(authHeaders!=null&& authHeaders.startsWith("Bearer")){
            token=authHeaders.substring(7);
            username= jwtUtils.extractUsername(token);
        }
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= cartUserDetailsService.loadUserByUsername(username);
            if(jwtUtils.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
