package com.example.demo.controllers;

import com.example.demo.models.Ranking;
import com.example.demo.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired(required = false)
    private RankingService rankingService;

    @GetMapping
    public List<Ranking> obtenerTodos() {
        return rankingService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ranking> obtenerPorId(@PathVariable int id) {
        Optional<Ranking> ranking = rankingService.obtenerPorId(id);
        return ranking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ranking crearRanking(@RequestBody Ranking ranking) {
        return rankingService.crearOActualizarRanking(ranking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ranking> actualizarRanking(@PathVariable int id, @RequestBody Ranking ranking) {
        if (rankingService.obtenerPorId(id).isPresent()) {
            ranking.setId(id);
            return ResponseEntity.ok(rankingService.crearOActualizarRanking(ranking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRanking(@PathVariable int id) {
        if (rankingService.obtenerPorId(id).isPresent()) {
            rankingService.eliminarRanking(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
