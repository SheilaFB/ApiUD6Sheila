package com.example.apiud6sheila.repositorio;

import com.example.apiud6sheila.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepositorio extends JpaRepository<Juego,Long> {
}
