package com.example.apiud6sheila.repositorio;

import com.example.apiud6sheila.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntuacionRepositorio extends JpaRepository<Puntuacion,Long> {
}
