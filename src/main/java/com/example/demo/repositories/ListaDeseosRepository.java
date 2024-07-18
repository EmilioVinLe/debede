package com.example.demo.repositories;

import com.example.demo.models.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Integer> {
}
