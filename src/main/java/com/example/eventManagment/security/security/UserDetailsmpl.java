package com.example.eventManagment.security.security;


import com.example.eventManagment.models.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsmpl implements UserDetails {

    private Long id;
    private String username;
    private String email;

    private Collection<? extends GrantedAuthority> ruoli;

    @JsonIgnore
    private String password;


    public static UserDetailsmpl costruisciDettagli(Utente user){
        Collection<GrantedAuthority> ruoli= Collections.singletonList(
                new SimpleGrantedAuthority(user.getRuolo().name()));


        return new  UserDetailsmpl(user.getId_utente(),user.getUsername(),user.getEmail(), ruoli,user.getPassword());

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruoli;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
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
