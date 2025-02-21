package com.example.eventManagment.payload;

import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.models.Evento;
import com.example.eventManagment.models.Prenotazione;
import com.example.eventManagment.models.Roulo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
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

}
