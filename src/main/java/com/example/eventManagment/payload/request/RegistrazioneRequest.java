package com.example.eventManagment.payload.request;

import com.example.eventManagment.models.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrazioneRequest {

    @NotEmpty
    @Size(min = 3, max = 15)
    private String username;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;

    @NotEmpty
    private String cognome;

    private String nome;
    @NotEmpty
    @Email
    private String email;


    private Ruolo ruolo;
}
