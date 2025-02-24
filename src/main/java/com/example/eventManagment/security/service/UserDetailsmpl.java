package com.example.eventManagment.security.service;


import com.example.eventManagment.models.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsmpl implements UserDetails {

    private Long id;
    private String username;
    private String email;

    private Collection<? extends GrantedAuthority> ruoli;

    @JsonIgnore
    private String password;


    public static UserDetailsmpl costruisciDettagli(Utente user){
        List<GrantedAuthority> ruoliUtente = user.getRuoli().stream()
                .map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome().name()))
                .collect(Collectors.toList());


        return new UserDetailsmpl(
                user.getId_utente(),
                user.getUsername(),
                user.getEmail(),
                ruoliUtente,
                user.getPassword()
        );

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruoli;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
}}
