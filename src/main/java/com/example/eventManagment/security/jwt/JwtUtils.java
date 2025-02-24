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

    public String creaJwtToken(Authentication authentication) {
        UserDetailsmpl userDetails = (UserDetailsmpl) authentication.getPrincipal();

        //Ora costruiamo il jwt
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirations))
                .signWith(getKey(), SignatureAlgorithm.HS256)   //Firma token con chiave segreta.
                .compact();   //converte in stringa
    }


    //recupero l'username dal jwt
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token) // ✅ Usa parseClaimsJws() per token firmati!
                .getBody()
                .getSubject();
    }

    // recupero scadenza token

    public Date getDateOfToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJwt(token).getBody().getExpiration();

    }

    //validazione del Token
    public boolean valdiazioneJwtToken(String token){
        try {
            Jwts.parser().setSigningKey(getKey()).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
}

    //recupero chiave


    public Key getKey(){

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }



}
