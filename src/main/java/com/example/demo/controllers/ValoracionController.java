package com.example.demo.controllers;

import com.example.demo.models.Valoracion;
import com.example.demo.services.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {

    @Autowired(required = false)
    private ValoracionService valoracionService;

    @PostMapping("/dejar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Valoracion> dejarValoracion(@RequestParam Long usuarioId, @RequestParam Long productoId, @RequestParam String descripcion) {
        try {
            Valoracion valoracion = valoracionService.dejarValoracion(usuarioId, productoId, descripcion);
            return ResponseEntity.ok(valoracion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<Valoracion> obtenerTodos() {
        return valoracionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> obtenerPorId(@PathVariable int id) {
        Optional<Valoracion> valoracion = valoracionService.obtenerPorId(id);
        return valoracion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public Valoracion crearValoracion(@RequestBody Valoracion valoracion) {
        return valoracionService.crearOActualizarValoracion(valoracion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Valoracion> actualizarValoracion(@PathVariable int id, @RequestBody Valoracion valoracion) {
        if (valoracionService.obtenerPorId(id).isPresent()) {
            valoracion.setId(id);
            return ResponseEntity.ok(valoracionService.crearOActualizarValoracion(valoracion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarValoracion(@PathVariable int id) {
        if (valoracionService.obtenerPorId(id).isPresent()) {
            valoracionService.eliminarValoracion(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
