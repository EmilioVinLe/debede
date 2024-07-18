package com.example.demo.repositories;

import com.example.demo.models.CarroCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroComprasRepository extends JpaRepository<CarroCompras, Integer> {
}
