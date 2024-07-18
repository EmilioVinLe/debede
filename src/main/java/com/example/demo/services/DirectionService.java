package com.example.demo.services;

import com.example.demo.models.Direction;
import com.example.demo.repositories.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionService {

    @Autowired(required = false)
    private DirectionRepository directionRepository;

    public List<Direction> obtenerTodos() {
        return directionRepository.findAll();
    }

    public Optional<Direction> obtenerPorId(int id) {
        return directionRepository.findById(id);
    }

    public Direction crearOActualizarDirection(Direction direction) {
        return directionRepository.save(direction);
    }

    public void eliminarDirection(int id) {
        directionRepository.deleteById(id);
    }
}
