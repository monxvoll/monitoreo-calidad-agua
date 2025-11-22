package com.monitoreo.agua.service;

import com.monitoreo.agua.entity.Auditoria;
import com.monitoreo.agua.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public List<Auditoria> obtenerHistorialCompleto() {
        return auditoriaRepository.findAllByOrderByFechaOperacionDesc();
    }
}