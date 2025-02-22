package com.example.eventManagment.payload;

import com.example.eventManagment.models.Eruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;


@Data
public class UtenteDTO {


    @NotBlank(message = "il nome è un campo obbligatorio")
    private String nome;

    @NotBlank(message = "il cognome è un campo obbligatorio")
    private String cognome;

    @NotBlank(message = "il username è un campo obbligatorio")
    private String username;

    @NotBlank(message = "la password è un campo obbligatorio")
    private String password;

    @NotBlank(message = "l'email è un campo obbligatorio")
    @Email(message = "indirizzo email non valido")
    private String email;

    private Set<String> ruoli;

}
