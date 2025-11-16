package com.monitoreo.agua.controller;

import com.monitoreo.agua.dto.MuestraConMedicionesDTO;
import com.monitoreo.agua.entity.Muestra;
import com.monitoreo.agua.service.MuestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/muestras")
@CrossOrigin(origins = "*")
public class MuestraController {

    @Autowired
    private MuestraService muestraService;

    // POST - Registrar muestra con mediciones
    @PostMapping
    public ResponseEntity<Muestra> registrarMuestraConMediciones(@RequestBody MuestraConMedicionesDTO dto) {
        try {
            Muestra muestra = muestraService.registrarMuestraConMediciones(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(muestra);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}