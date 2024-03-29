package com.example.apiud6sheila.controlador;

/*
 * Clase: JuegoControlador
 * Autor: Sheila Feijoo
 * Fecha de creación: 2024
 * Descripción-Enunciado: Clase controlador de juegos
 */

import com.example.apiud6sheila.modelo.Juego;
import com.example.apiud6sheila.repositorio.JuegoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/juego")
public class JuegoControlador {

    @Autowired
    private JuegoRepositorio juegoRepositorio;

    //Listar todos los juegos
    @GetMapping
    public List<Juego> listarTodos(){
        return juegoRepositorio.findAll();
    }

    //Listar todos los juegos por orden alfabético
    @GetMapping("/alfabetico")
    public List<Juego> listarAlfabetico(){
        return juegoRepositorio.findAll(Sort.by(Sort.Direction.ASC,"nombre"));
    }

    //Obtener un juego por su id
    @GetMapping("/{id}")
    public Juego obtenerJuego(@PathVariable long id){
        Optional<Juego> juego=  juegoRepositorio.findById(id);
        return juego.orElseThrow(()->
                new RuntimeException("Juego no encontrado"));
    }

    //añadir un juego
    @PostMapping
    public Juego añadirJuego(@RequestBody Juego juego) {
            return juegoRepositorio.save(juego);
    }

    //Editar un juego. Si algún valor es nulo no lo modifica. Si aunque no sea nulo está vacío tampoco lo modifica.
    //Si no lo puede modificar por que está nulo o vacío va a devolver el registro sin modificar para evitar errores.
    //Si ocurre algún otro error devolverá que no puede modificar el juego.
    //Podría modificarse si se quiesiera para que en vez de devolver el registro sin modificar devolviese el error.
    @PutMapping("/{id}")
    public Juego editarJuego(@PathVariable long id, @RequestBody Juego juego){
        return juegoRepositorio.findById(id).map(juegoTemp ->{
            if (juego.getNombre()!=null && juego.getPlataforma()!=null && juego.getImagen()!=null) {
                juegoTemp.setNombre(!(juego.getNombre().isBlank()) ? juego.getNombre() : juegoTemp.getNombre());
                juegoTemp.setPlataforma(!(juego.getPlataforma().isBlank()) ? juego.getPlataforma() : juegoTemp.getPlataforma());
                juegoTemp.setImagen(!(juego.getImagen().isBlank()) ? juego.getImagen() : juegoTemp.getImagen());
                return juegoRepositorio.save(juegoTemp);
            }
            return null;

        }).orElseThrow(()->
                new RuntimeException("No se puede editar"));
    }

    //Eliminar un juego
    @DeleteMapping("/{id}")
    public void eliminarJuego(@PathVariable long id){
        juegoRepositorio.deleteById(id);
    }
}
