package com.monitoreo.agua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "SENSORES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sensores")
    @SequenceGenerator(name = "seq_sensores", sequenceName = "SEQ_SENSORES", allocationSize = 1)
    @Column(name = "ID_SENSOR")
    private Long idSensor;

    @Column(name = "CODIGO_SENSOR", nullable = false, length = 50)
    private String codigoSensor;

    @Column(name = "UBICACION", precision = 10, scale = 6)
    private BigDecimal ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RIO")
    @JsonIgnore
    private Rio rio;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Muestra> muestras;
}