package com.example.demo.controllers;

import com.example.demo.models.CarroCompras;
import com.example.demo.services.CarroComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carroCompras")
public class CarroComprasController {

    @Autowired(required = false)
    private CarroComprasService carroComprasService;

    @GetMapping
    public List<CarroCompras> obtenerTodos() {
        return carroComprasService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroCompras> obtenerPorId(@PathVariable int id) {
        Optional<CarroCompras> carroCompras = carroComprasService.obtenerPorId(id);
        return carroCompras.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarroCompras crearCarroCompras(@RequestBody CarroCompras carroCompras) {
        return carroComprasService.crearOActualizarCarroCompras(carroCompras);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroCompras> actualizarCarroCompras(@PathVariable int id, @RequestBody CarroCompras carroCompras) {
        if (carroComprasService.obtenerPorId(id).isPresent()) {
            carroCompras.setId(id);
            return ResponseEntity.ok(carroComprasService.crearOActualizarCarroCompras(carroCompras));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarroCompras(@PathVariable int id) {
        if (carroComprasService.obtenerPorId(id).isPresent()) {
            carroComprasService.eliminarCarroCompras(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
