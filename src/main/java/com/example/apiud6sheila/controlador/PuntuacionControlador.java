package com.example.apiud6sheila.controlador;


import com.example.apiud6sheila.modelo.Puntuacion;
import com.example.apiud6sheila.repositorio.JuegoRepositorio;
import com.example.apiud6sheila.repositorio.PuntuacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/puntuacion")
public class PuntuacionControlador {

    @Autowired
    private PuntuacionRepositorio puntuacionRepositorio;

    @Autowired
    private JuegoRepositorio juegoRepositorio;

    //listar todos las puntuaciones
    @GetMapping
    public List<Puntuacion> listarPuntuaciones(){

        return puntuacionRepositorio.findAll();
    }

    //listar las puntuaciones de un juego de mayor a menor
    @GetMapping("/juego/{id}")
    public List<Puntuacion> listarPorJuego(@PathVariable Long id){
        return puntuacionRepositorio.findByJuegoId(id);
    }

    //listar todas las puntuaciones ordenadas por juego y puntuación
    @GetMapping("/ordenadasporjuego")
    public List<Puntuacion> listarPuntuacionesOrdenadasPorJuegoYPuntuacion(){
        return puntuacionRepositorio.findAll(Sort.by(Sort.Direction.ASC, "juego").and(Sort.by(Sort.Direction.DESC, "puntuacion")));
    }


    //Obtener una puntuacion
    @GetMapping("/{id}")
    public Puntuacion obtenerPuntuacion(@PathVariable long id){
        Optional<Puntuacion> resultado = puntuacionRepositorio.findById(id);
        return resultado.orElseThrow(()->
                new RuntimeException("Puntuación no encontrada"));
    }

    //Alta de una puntuación
    @PostMapping("/juego/{id}")
    public Puntuacion crearPuntuacion(@PathVariable long id, @RequestBody Puntuacion puntuacion){
        Puntuacion punt = juegoRepositorio.findById(id).map(juego -> {
            puntuacion.setJuego(juego);
            return puntuacionRepositorio.save(puntuacion);
        }).orElseThrow(()->
                new RuntimeException("Juego no encontrado"));
        return punt;
    }

    //Editar una datos puntuación
    @PutMapping("/{id}")
    public Puntuacion editarPuntuacion(@PathVariable long id, @RequestBody Puntuacion puntuacion){
        return puntuacionRepositorio.findById(id).map(puntuacionTemp ->{
            puntuacionTemp.setNombre((puntuacion.getNombre()!=null)?puntuacion.getNombre():puntuacionTemp.getNombre());
            puntuacionTemp.setPuntuacion((puntuacion.getPuntuacion()!=0?puntuacion.getPuntuacion(): puntuacionTemp.getPuntuacion()));
            return puntuacionRepositorio.save(puntuacionTemp);
        }).orElseThrow();
    }

    //Eliminar una puntuación
    @DeleteMapping("/{id}")
    public void eliminarPuntuacion(@PathVariable long id){
        puntuacionRepositorio.deleteById(id);
    }

    @GetMapping("/record")
    public List<Puntuacion> getRecord(){
        return puntuacionRepositorio.findAllRecords();
    }



}
