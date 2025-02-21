package com.example.eventManagment.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Roulo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id_ruolo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
     private Eruolo ruolo;


}
