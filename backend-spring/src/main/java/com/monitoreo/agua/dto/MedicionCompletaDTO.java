package com.monitoreo.agua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicionCompletaDTO {
    private String rio;
    private String codigoSensor;
    private LocalDateTime fechaMuestra;
    private String parametro;
    private BigDecimal valor;
    private String unidad;
}