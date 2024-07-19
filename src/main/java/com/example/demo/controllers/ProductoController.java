package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired(required = false)
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/top_vendidos")
    public List<Producto> obtenerTodosVendidosOrden() {
        return productoService.obtenerTodosOrdenVendidos();
    }

    @GetMapping("/categoria")
    public List<Producto> obtenerTodosCategoriaOrden() {
        return productoService.obtenerTodosPorCategoria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('PYME')")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearOActualizarProducto(producto);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PYME')")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (productoService.obtenerPorId(id).isPresent()) {
            producto.setId(id);
            return ResponseEntity.ok(productoService.crearOActualizarProducto(producto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PYME')")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoService.obtenerPorId(id).isPresent()) {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ranking/{pymeId}")
    public List<Producto> obtenerRankingPorPymeId(@PathVariable int pymeId) {
        return productoService.obtenerTopVendidosPorPymeId(pymeId);
    }
}
