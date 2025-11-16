package com.monitoreo.agua.repository;

import com.monitoreo.agua.entity.Muestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Long> {
}