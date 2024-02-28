package com.example.apiud6sheila.repositorio;

/*
 * Clase: PuntuacionRepositorio
 * Autor: Sheila Feijoo
 * Fecha de creación: 2024
 * Descripción-Enunciado: Interfaz repostitorio de Puntuacion para operaciones CRUD.
 */

import com.example.apiud6sheila.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntuacionRepositorio extends JpaRepository<Puntuacion,Long> {
    List<Puntuacion> findByJuegoIdOrderByPuntuacionDesc(Long idJuego);
    @Query("SELECT p FROM puntuaciones p WHERE p.puntuacion = (SELECT MAX(p2.puntuacion) FROM puntuaciones p2 WHERE p.juego.id = p2.juego.id)")
    List <Puntuacion> findAllRecords();

}
