package com.example.eventManagment.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// ERRORI DI AUTENTICAZIONE

public class AuthTokenFilter implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        //set
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


        //contenuto di ritorno
        final Map<String,Object> body= new HashMap<>();
        body.put("stato",HttpServletResponse.SC_UNAUTHORIZED);
        body.put("errore","Autorizzazione non valida");
        body.put("messaggio", authException.getMessage());
        body.put("path",request.getServletPath());

        //conversione Map in JSON

        final ObjectMapper mappatturaErrori= new ObjectMapper();
        mappatturaErrori.writeValue(response.getOutputStream(),body);
    }

}
