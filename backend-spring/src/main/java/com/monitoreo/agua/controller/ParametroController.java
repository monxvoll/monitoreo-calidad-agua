package com.monitoreo.agua.controller;

import com.monitoreo.agua.entity.Parametro;
import com.monitoreo.agua.service.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parametros")
@CrossOrigin(origins = "*")
public class ParametroController {

    @Autowired
    private ParametroService parametroService;

    // GET - Obtener todos los parametros
    @GetMapping
    public ResponseEntity<List<Parametro>> obtenerTodos() {
        return ResponseEntity.ok(parametroService.obtenerTodos());
    }

    // GET - Obtener parametro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Parametro> obtenerPorId(@PathVariable Long id) {
        return parametroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nuevo parametro
    @PostMapping
    public ResponseEntity<Parametro> crear(@RequestBody Parametro parametro) {
        Parametro nuevoParametro = parametroService.crear(parametro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoParametro);
    }

    // PUT - Actualizar parametro existente
    @PutMapping("/{id}")
    public ResponseEntity<Parametro> actualizar(@PathVariable Long id, @RequestBody Parametro parametro) {
        try {
            Parametro parametroActualizado = parametroService.actualizar(id, parametro);
            return ResponseEntity.ok(parametroActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar parametro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        parametroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}