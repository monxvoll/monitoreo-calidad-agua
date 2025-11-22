package com.monitoreo.agua.controller;

import com.monitoreo.agua.entity.Sensor;
import com.monitoreo.agua.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensores")
@CrossOrigin(origins = "*")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    // GET - Obtener todos los sensores
    @GetMapping
    public ResponseEntity<List<Sensor>> obtenerTodos() {
        return ResponseEntity.ok(sensorService.obtenerTodos());
    }

    // GET - Obtener sensor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> obtenerPorId(@PathVariable Long id) {
        return sensorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nuevo sensor
    @PostMapping
    public ResponseEntity<Sensor> crear(@RequestBody Sensor sensor) {
        Sensor nuevoSensor = sensorService.crear(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSensor);
    }

    // PUT - Actualizar sensor existente
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> actualizar(@PathVariable Long id, @RequestBody Sensor sensor) {
        try {
            Sensor sensorActualizado = sensorService.actualizar(id, sensor);
            return ResponseEntity.ok(sensorActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar sensor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sensorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}