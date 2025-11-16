package com.monitoreo.agua.controller;

import com.monitoreo.agua.dto.MedicionCompletaDTO;
import com.monitoreo.agua.dto.PromedioPhDTO;
import com.monitoreo.agua.dto.RioConSensoresDTO;
import com.monitoreo.agua.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // GET - Reporte de Rios y Sensores
    @GetMapping("/rios-y-sensores")
    public ResponseEntity<List<RioConSensoresDTO>> obtenerRiosYSensores() {
        List<RioConSensoresDTO> reportes = reporteService.obtenerRiosYSensores();
        return ResponseEntity.ok(reportes);
    }

    // GET - Mediciones Completas (une las 5 tablas)
    @GetMapping("/mediciones-completas")
    public ResponseEntity<List<MedicionCompletaDTO>> obtenerMedicionesCompletas() {
        List<MedicionCompletaDTO> reportes = reporteService.obtenerMedicionesCompletas();
        return ResponseEntity.ok(reportes);
    }

    // GET - Promedio de pH por Rio
    @GetMapping("/promedio-ph")
    public ResponseEntity<List<PromedioPhDTO>> obtenerPromedioPh() {
        List<PromedioPhDTO> reportes = reporteService.obtenerPromedioPh();
        return ResponseEntity.ok(reportes);
    }
}