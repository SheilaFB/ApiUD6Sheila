package com.example.apiud6sheila.repositorio;

import com.example.apiud6sheila.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntuacionRepositorio extends JpaRepository<Puntuacion,Long> {
    List<Puntuacion> findByJuegoId(Long idJuego);

}
