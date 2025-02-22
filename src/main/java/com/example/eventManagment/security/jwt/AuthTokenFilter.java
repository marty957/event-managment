package com.example.eventManagment.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;




public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired JwtUtils utils;
    @Autowired
    UserDetailsService userDetailsService;


    private String analizeJwt(HttpServletRequest request){
       String headAuth= request.getHeader("Authorization");
       if(StringUtils.hasText(headAuth) && (headAuth.startsWith("Bearer "))){

           return headAuth.substring(7);
       }
       return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // otteniamo JWT dai cookie http
        String jwt=analizeJwt(request);

        //se ce il jwt lo convalidiamo

        if(jwt!=null && utils.valdiazioneJwtToken(jwt)){


            //recuper username dal toke
            String username=utils.getUsernameFromToken(jwt);
            //recupero UserDetails da username e crea un object authentication
             UserDetails userDetails= userDetailsService.loadUserByUsername(username);
            // creo un oggetto UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(
                          userDetails,null,userDetails.getAuthorities()
                    );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            filterChain.doFilter(request, response);
        }

    }
}
