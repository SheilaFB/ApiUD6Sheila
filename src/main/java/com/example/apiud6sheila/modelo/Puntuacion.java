package com.example.apiud6sheila.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "puntuaciones")
public class Puntuacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;

    @Min(value = 0,message = "No puede ser menor que 0")
    private long puntuacion;

    @ManyToOne
    @JoinColumn(name="id_juego")
    private Juego juego;

    public Puntuacion(){}

    public Puntuacion(String nombre, long puntuacion){
        this.nombre=nombre;
        this.puntuacion=puntuacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(long puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}
