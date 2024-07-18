package com.example.demo.controllers;

import com.example.demo.models.ListaDeseos;
import com.example.demo.services.ListaDeseosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/listaDeseos")
public class ListaDeseosController {

    @Autowired(required = false)
    private ListaDeseosService listaDeseosService;

    @GetMapping
    public List<ListaDeseos> obtenerTodos() {
        return listaDeseosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaDeseos> obtenerPorId(@PathVariable int id) {
        Optional<ListaDeseos> listaDeseos = listaDeseosService.obtenerPorId(id);
        return listaDeseos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ListaDeseos crearListaDeseos(@RequestBody ListaDeseos listaDeseos) {
        return listaDeseosService.crearOActualizarListaDeseos(listaDeseos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaDeseos> actualizarListaDeseos(@PathVariable int id, @RequestBody ListaDeseos listaDeseos) {
        if (listaDeseosService.obtenerPorId(id).isPresent()) {
            listaDeseos.setId(id);
            return ResponseEntity.ok(listaDeseosService.crearOActualizarListaDeseos(listaDeseos));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarListaDeseos(@PathVariable int id) {
        if (listaDeseosService.obtenerPorId(id).isPresent()) {
            listaDeseosService.eliminarListaDeseos(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
