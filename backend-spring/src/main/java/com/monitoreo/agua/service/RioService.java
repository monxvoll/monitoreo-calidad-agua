package com.monitoreo.agua.service;

import com.monitoreo.agua.entity.Rio;
import com.monitoreo.agua.repository.RioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RioService {

    @Autowired
    private RioRepository rioRepository;

    // GET (todos)
    public List<Rio> obtenerTodos() {
        return rioRepository.findAll();
    }

    // GET (por id)
    public Optional<Rio> obtenerPorId(Long id) {
        return rioRepository.findById(id);
    }

    // POST (crear)
    public Rio crear(Rio rio) {
        return rioRepository.save(rio);
    }

    // PUT (actualizar)
    public Rio actualizar(Long id, Rio rioActualizado) {
        return rioRepository.findById(id)
                .map(rio -> {
                    rio.setNombre(rioActualizado.getNombre());
                    rio.setLongitud(rioActualizado.getLongitud());
                    return rioRepository.save(rio);
                })
                .orElseThrow(() -> new RuntimeException("Rio no encontrado con id: " + id));
    }

    // DELETE (borrar)
    public void eliminar(Long id) {
        rioRepository.deleteById(id);
    }
}