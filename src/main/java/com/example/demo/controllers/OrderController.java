package com.example.demo.controllers;

import com.example.demo.models.Order;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired(required = false)
    private OrderService orderService;

    @GetMapping
    public List<Order> obtenerTodos() {
        return orderService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> obtenerPorId(@PathVariable Long id) {
        Optional<Order> order = orderService.obtenerPorId(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order crearOrder(@RequestBody Order order) {
        return orderService.crearOActualizarOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> actualizarOrder(@PathVariable Long id, @RequestBody Order order) {
        if (orderService.obtenerPorId(id).isPresent()) {
            order.setId(id);
            return ResponseEntity.ok(orderService.crearOActualizarOrder(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrder(@PathVariable Long id) {
        if (orderService.obtenerPorId(id).isPresent()) {
            orderService.eliminarOrder(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
