package com.monitoreo.agua.repository;

import com.monitoreo.agua.entity.Rio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RioRepository extends JpaRepository<Rio, Long> {
}