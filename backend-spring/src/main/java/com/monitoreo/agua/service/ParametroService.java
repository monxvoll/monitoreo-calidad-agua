package com.monitoreo.agua.service;

import com.monitoreo.agua.entity.Parametro;
import com.monitoreo.agua.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    // GET (todos)
    public List<Parametro> obtenerTodos() {
        return parametroRepository.findAll();
    }

    // GET (por id)
    public Optional<Parametro> obtenerPorId(Long id) {
        return parametroRepository.findById(id);
    }

    // POST (crear)
    public Parametro crear(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    // PUT (actualizar)
    public Parametro actualizar(Long id, Parametro parametroActualizado) {
        return parametroRepository.findById(id)
                .map(parametro -> {
                    parametro.setTipo(parametroActualizado.getTipo());
                    parametro.setUnidad(parametroActualizado.getUnidad());
                    return parametroRepository.save(parametro);
                })
                .orElseThrow(() -> new RuntimeException("Parametro no encontrado con id: " + id));
    }

    // DELETE (borrar)
    public void eliminar(Long id) {
        parametroRepository.deleteById(id);
    }
}