package com.example.demo.services;

import com.example.demo.models.CarroCompras;
import com.example.demo.models.Producto;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.CarroComprasRepository;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroComprasService {

    @Autowired(required = false)
    private CarroComprasRepository carroComprasRepository;
    @Autowired(required = false)
    private ProductoRepository productoRepository;

    public List<CarroCompras> obtenerTodos() {
        return carroComprasRepository.findAll();
    }

    public Optional<CarroCompras> obtenerPorId(int id) {
        return carroComprasRepository.findById(id);
    }

    public CarroCompras crearOActualizarCarroCompras(CarroCompras carroCompras) {
        return carroComprasRepository.save(carroCompras);
    }

    public void eliminarCarroCompras(int id) {
        carroComprasRepository.deleteById(id);
    }

    public void agregarProductoACarroCompras(Usuario usuario, Long productoId) {
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (producto.getStock() > 0) {
            CarroCompras carroCompras = usuario.getCarroCompras();
            carroCompras.getProductosList().add(producto);
            carroComprasRepository.save(carroCompras);
        } else {
            throw new RuntimeException("No hay stock disponible para este producto");
        }
    }
}
