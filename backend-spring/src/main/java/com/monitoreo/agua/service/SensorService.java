package com.monitoreo.agua.service;

import com.monitoreo.agua.entity.Sensor;
import com.monitoreo.agua.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    // GET (todos)
    public List<Sensor> obtenerTodos() {
        return sensorRepository.findAll();
    }

    // GET (por id)
    public Optional<Sensor> obtenerPorId(Long id) {
        return sensorRepository.findById(id);
    }

    // POST (crear)
    public Sensor crear(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    // PUT (actualizar)
    public Sensor actualizar(Long id, Sensor sensorActualizado) {
        return sensorRepository.findById(id)
                .map(sensor -> {
                    sensor.setCodigoSensor(sensorActualizado.getCodigoSensor());
                    sensor.setUbicacion(sensorActualizado.getUbicacion());
                    sensor.setRio(sensorActualizado.getRio());
                    return sensorRepository.save(sensor);
                })
                .orElseThrow(() -> new RuntimeException("Sensor no encontrado con id: " + id));
    }

    // DELETE (borrar)
    public void eliminar(Long id) {
        sensorRepository.deleteById(id);
    }
}