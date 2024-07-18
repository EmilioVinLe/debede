package com.example.demo.repositories;

import com.example.demo.models.Producto;
import com.example.demo.models.Usuario;
import com.example.demo.models.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
    Optional<Valoracion> findByUsuarioValoracionAndProductoValoracion(Usuario usuario, Producto producto);
}
