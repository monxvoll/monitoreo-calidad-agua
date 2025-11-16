package com.monitoreo.agua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromedioPhDTO {
    private String rio;
    private BigDecimal promedioPh;
}