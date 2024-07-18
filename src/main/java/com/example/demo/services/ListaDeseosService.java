package com.example.demo.services;

import com.example.demo.models.ListaDeseos;
import com.example.demo.models.Producto;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.ListaDeseosRepository;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaDeseosService {

    @Autowired(required = false)
    private ListaDeseosRepository listaDeseosRepository;
    @Autowired(required = false)
    private ProductoRepository productoRepository;

    public List<ListaDeseos> obtenerTodos() {
        return listaDeseosRepository.findAll();
    }

    public Optional<ListaDeseos> obtenerPorId(int id) {
        return listaDeseosRepository.findById(id);
    }

    public ListaDeseos crearOActualizarListaDeseos(ListaDeseos listaDeseos) {
        return listaDeseosRepository.save(listaDeseos);
    }

    public void eliminarListaDeseos(int id) {
        listaDeseosRepository.deleteById(id);
    }

    public void agregarProductoAListaDeseos(Usuario usuario, Long productoId) {
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (producto.getStock() > 0) {
            ListaDeseos listaDeseos = usuario.getListaDeseos();
            listaDeseos.getProductosListaDeseo().add(producto);
            listaDeseosRepository.save(listaDeseos);
        } else {
            throw new RuntimeException("No hay stock disponible para este producto");
        }
    }
}
