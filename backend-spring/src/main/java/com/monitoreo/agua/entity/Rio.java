package com.monitoreo.agua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "RIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rio")
    @SequenceGenerator(name = "seq_rio", sequenceName = "SEQ_RIO", allocationSize = 1)
    @Column(name = "ID_RIO")
    private Long idRio;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "LONGITUD", precision = 10, scale = 2)
    private BigDecimal longitud;

    @OneToMany(mappedBy = "rio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sensor> sensores;
}