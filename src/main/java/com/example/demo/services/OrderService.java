package com.example.demo.services;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired(required = false)
    private OrderRepository orderRepository;

    public List<Order> obtenerTodos() {
        return orderRepository.findAll();
    }

    public Optional<Order> obtenerPorId(Long id) {
        return orderRepository.findById(id);
    }

    public Order crearOActualizarOrder(Order order) {
        return orderRepository.save(order);
    }

    public void eliminarOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
