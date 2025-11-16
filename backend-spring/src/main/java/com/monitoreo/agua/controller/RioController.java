package com.monitoreo.agua.controller;

import com.monitoreo.agua.entity.Rio;
import com.monitoreo.agua.service.RioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rios")
@CrossOrigin(origins = "*")
public class RioController {

    @Autowired
    private RioService rioService;

    // GET - Obtener todos los rios
    @GetMapping
    public ResponseEntity<List<Rio>> obtenerTodos() {
        return ResponseEntity.ok(rioService.obtenerTodos());
    }

    // GET - Obtener rio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rio> obtenerPorId(@PathVariable Long id) {
        return rioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nuevo rio
    @PostMapping
    public ResponseEntity<Rio> crear(@RequestBody Rio rio) {
        Rio nuevoRio = rioService.crear(rio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRio);
    }

    // PUT - Actualizar rio existente
    @PutMapping("/{id}")
    public ResponseEntity<Rio> actualizar(@PathVariable Long id, @RequestBody Rio rio) {
        try {
            Rio rioActualizado = rioService.actualizar(id, rio);
            return ResponseEntity.ok(rioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar rio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}