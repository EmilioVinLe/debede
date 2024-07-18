package com.example.demo.services;

import com.example.demo.models.Producto;
import com.example.demo.models.Usuario;
import com.example.demo.models.Valoracion;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.repositories.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionService {

    @Autowired(required = false)
    private ValoracionRepository valoracionRepository;

    @Autowired(required = false)
    private ProductoRepository productoRepository;
    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    public Valoracion dejarValoracion(Long usuarioId, Long productoId, String descripcion) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<Valoracion> existingValoracion = valoracionRepository.findByUsuarioValoracionAndProductoValoracion(usuario, producto);
        if (existingValoracion.isPresent()) {
            throw new RuntimeException("El usuario ya ha valorado este producto");
        }

        Valoracion valoracion = new Valoracion();
        valoracion.setUsuarioValoracion(usuario);
        valoracion.setProductoValoracion(producto);
        valoracion.setDescripcion(descripcion);

        return valoracionRepository.save(valoracion);
    }

    public List<Valoracion> obtenerTodos() {
        return valoracionRepository.findAll();
    }

    public Optional<Valoracion> obtenerPorId(int id) {
        return valoracionRepository.findById(id);
    }

    public Valoracion crearOActualizarValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    public void eliminarValoracion(int id) {
        valoracionRepository.deleteById(id);
    }
}
