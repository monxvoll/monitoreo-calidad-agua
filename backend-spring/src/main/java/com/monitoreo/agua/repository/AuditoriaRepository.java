package com.monitoreo.agua.repository;

import com.monitoreo.agua.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    // SELECT * FROM AUDITORIA ORDER BY FECHA_OPERACION DESC
    List<Auditoria> findAllByOrderByFechaOperacionDesc();
}