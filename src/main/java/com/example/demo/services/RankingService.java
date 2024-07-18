package com.example.demo.services;

import com.example.demo.models.Ranking;
import com.example.demo.repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankingService {

    @Autowired(required = false)
    private RankingRepository rankingRepository;

    public List<Ranking> obtenerTodos() {
        return rankingRepository.findAll();
    }

    public Optional<Ranking> obtenerPorId(int id) {
        return rankingRepository.findById(id);
    }

    public Ranking crearOActualizarRanking(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    public void eliminarRanking(int id) {
        rankingRepository.deleteById(id);
    }
}
