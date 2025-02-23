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

@Value("${jwtSecret}")
private String jwtSecret;

@Value("${jwtExpirations}")
private int jwtExpirations;

    // creo il jwt

public String cretJwtToken(Authentication authentication){
    if (authentication == null || !authentication.isAuthenticated()) {
        throw new IllegalArgumentException("L'autenticazione non è valida");
    }
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetailsmpl utentePrincipal) { // Uso di pattern matching per il tipo
        String username = utentePrincipal.getUsername();

        // Controllo che l'username sia non nullo e non vuoto
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("L'username non può essere nullo o vuoto");
        }

        // Creazione del token JWT
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirations))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    } else {
        throw new IllegalArgumentException("Il principale non è di tipo UserDetailsmpl");
    }


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
