package com.example.apiud6sheila.repositorio;

/*
 * Clase: JuegoRepositorio
 * Autor: Sheila Feijoo
 * Fecha de creación: 2024
 * Descripción-Enunciado: Interfaz repostitorio de Juegos para operaciones CRUD.
 */

import com.example.apiud6sheila.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepositorio extends JpaRepository<Juego,Long> {
}
