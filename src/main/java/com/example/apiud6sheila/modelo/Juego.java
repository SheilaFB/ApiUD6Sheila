package com.example.apiud6sheila.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String nombre;

    String plataforma;

    public Juego(){}

    public Juego(String nombre, String plataforma){
        this.nombre=nombre;
        this.plataforma=plataforma;
    }

    public Juego(long id, String nombre, String plataforma) {
        this.id = id;
        this.nombre = nombre;
        this.plataforma = plataforma;
    }
}
