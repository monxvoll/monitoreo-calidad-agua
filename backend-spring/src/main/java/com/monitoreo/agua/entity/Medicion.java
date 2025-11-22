package com.monitoreo.agua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "MEDICION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medicion")
    @SequenceGenerator(name = "seq_medicion", sequenceName = "SEQ_MEDICION", allocationSize = 1)
    @Column(name = "ID_MEDICION")
    private Long idMedicion;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 3)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MUESTRA")
    @JsonIgnore
    private Muestra muestra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARAMETRO")
    private Parametro parametro;
}