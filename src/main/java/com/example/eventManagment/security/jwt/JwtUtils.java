package com.example.eventManagment.security.jwt;



import com.example.eventManagment.security.service.UserDetailsmpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


//classe per generare il token
@Component
public class JwtUtils {

@Value("${jwt.Secret}")
private String jwtSecret;

@Value("${jwt.Expirations}")
private int jwtExpiration;

    // creo il jwt

public String cretJwtToken(Authentication authentication){
   UserDetailsmpl utentePrincipal= (UserDetailsmpl) authentication.getPrincipal();
     return Jwts.builder()
            .setSubject(utentePrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()+jwtExpiration))
            .signWith(getKey(), SignatureAlgorithm.ES256)
            .compact();


}

    //recupero l'username dal jwt
    public String getUsernameFromToken(String token) {
       String username= Jwts.parser().setSigningKey(getKey()).build().parseClaimsJwt(token).getBody().getSubject();
       return username;
    }

    // recupero scadenza token

    public Date getDateOfToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJwt(token).getBody().getExpiration();

    }

    //validazione del Token
    public boolean valdiazioneJwtToken(String token){
        Jwts.parser().setSigningKey(getKey()).build().parse(token);
        return true;
}

    //recupero chiave


    public Key getKey(){

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }



}
