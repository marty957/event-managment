package com.example.eventManagment.controllers;


import com.example.eventManagment.exception.EmailDuplicateException;
import com.example.eventManagment.exception.UsernameDuplicateException;
import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.payload.UtenteDTO;
import com.example.eventManagment.payload.request.LoginRequest;
import com.example.eventManagment.payload.request.RegistrazioneRequest;
import com.example.eventManagment.payload.response.JwtResponse;
import com.example.eventManagment.security.jwt.JwtUtils;
import com.example.eventManagment.security.service.UserDetailsmpl;
import com.example.eventManagment.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UtenteController {


    @Autowired
    UtenteService utenteService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/registrazione")
        public ResponseEntity<?> nuovoUtente (@Validated @RequestBody RegistrazioneRequest nuovoUtente, BindingResult validazione){
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori,HttpStatus.BAD_REQUEST);
        }


        try {
            String message=utenteService.insertUtente(nuovoUtente);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UsernameDuplicateException e) {
           return new ResponseEntity<>("Username gia presente",HttpStatus.CONFLICT);
        } catch (EmailDuplicateException e) {
            return new ResponseEntity<>("Email gia presente",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Validated @RequestBody LoginRequest loginDTO, BindingResult validazione) {
        if (validazione.hasErrors()) {

            StringBuilder errori = new StringBuilder("errori nella vadilazione dati \n");
            for (ObjectError errore : validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori, HttpStatus.BAD_REQUEST);
        }
        UsernamePasswordAuthenticationToken tokenAuth= new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());


        Authentication authentication= authenticationManager.authenticate(tokenAuth);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String finalToken=jwtUtils.cretJwtToken(authentication);

        UserDetailsmpl detailUser= (UserDetailsmpl) authentication.getPrincipal();

        List<String> ruoli=detailUser.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse jwtResponse=new JwtResponse(detailUser.getUsername(),detailUser.getId(),detailUser.getEmail(),ruoli,finalToken);


        return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
    }



}
