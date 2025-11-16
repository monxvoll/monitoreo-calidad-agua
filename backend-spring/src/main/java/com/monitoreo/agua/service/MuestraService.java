package com.monitoreo.agua.service;

import com.monitoreo.agua.dto.MedicionDTO;
import com.monitoreo.agua.dto.MuestraConMedicionesDTO;
import com.monitoreo.agua.entity.*;
import com.monitoreo.agua.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MuestraService {

    @Autowired
    private MuestraRepository muestraRepository;

    @Autowired
    private MedicionRepository medicionRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    @Transactional
    public Muestra registrarMuestraConMediciones(MuestraConMedicionesDTO dto) {
        // Buscar el sensor
        Sensor sensor = sensorRepository.findById(dto.getIdSensor())
                .orElseThrow(() -> new RuntimeException("Sensor no encontrado con id: " + dto.getIdSensor()));

        // Crear la muestra
        Muestra muestra = new Muestra();
        muestra.setFechaMuestra(dto.getFechaMuestra());
        muestra.setSensor(sensor);
        muestra.setMediciones(new ArrayList<>());

        // Guardar la muestra primero
        Muestra muestraGuardada = muestraRepository.save(muestra);

        // Crear las mediciones
        List<Medicion> mediciones = new ArrayList<>();
        for (MedicionDTO medicionDTO : dto.getMediciones()) {
            Parametro parametro = parametroRepository.findById(medicionDTO.getIdParametro())
                    .orElseThrow(() -> new RuntimeException("Parametro no encontrado con id: " + medicionDTO.getIdParametro()));

            Medicion medicion = new Medicion();
            medicion.setValor(medicionDTO.getValor());
            medicion.setMuestra(muestraGuardada);
            medicion.setParametro(parametro);

            Medicion medicionGuardada = medicionRepository.save(medicion);
            mediciones.add(medicionGuardada);
        }

        muestraGuardada.setMediciones(mediciones);
        return muestraGuardada;
    }
}