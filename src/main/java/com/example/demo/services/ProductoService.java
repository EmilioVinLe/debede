package com.example.demo.services;

import com.example.demo.models.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired(required = false)
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> obtenerTodosPorCategoria() {
        return productoRepository.findAllByOrderByCategoriaAsc();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crearOActualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> obtenerTodosOrdenVendidos() {
        return productoRepository.findAllByOrderByVendidosDesc();
    }

    public List<Producto> obtenerTopVendidosPorPymeId(int pymeId) {
        return productoRepository.findTopVendidosByPymeId(pymeId);
    }
}
