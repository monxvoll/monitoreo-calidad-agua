package com.monitoreo.agua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuestraConMedicionesDTO {
    private Long idSensor;
    private LocalDateTime fechaMuestra;
    private List<MedicionDTO> mediciones;
}