package com.example.eventManagment.security.security;

import com.example.eventManagment.models.Utente;
import com.example.eventManagment.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Utente utente= utenteRepository.findByUsername(username).orElseThrow();


       return UserDetailsmpl.costruisciDettagli(utente);
    }
}
