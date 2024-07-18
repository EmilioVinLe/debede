package com.example.demo.controllers;

import com.example.demo.models.Direction;
import com.example.demo.services.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/directions")
public class DirectionController {

    @Autowired(required = false)
    private DirectionService directionService;

    @GetMapping
    public List<Direction> obtenerTodos() {
        return directionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direction> obtenerPorId(@PathVariable int id) {
        Optional<Direction> direction = directionService.obtenerPorId(id);
        return direction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Direction crearDirection(@RequestBody Direction direction) {
        return directionService.crearOActualizarDirection(direction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direction> actualizarDirection(@PathVariable int id, @RequestBody Direction direction) {
        if (directionService.obtenerPorId(id).isPresent()) {
            direction.setId(id);
            return ResponseEntity.ok(directionService.crearOActualizarDirection(direction));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDirection(@PathVariable int id) {
        if (directionService.obtenerPorId(id).isPresent()) {
            directionService.eliminarDirection(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
