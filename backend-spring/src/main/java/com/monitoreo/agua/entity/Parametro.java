package com.monitoreo.agua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "PARAMETRO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parametro")
    @SequenceGenerator(name = "seq_parametro", sequenceName = "SEQ_PARAMETRO", allocationSize = 1)
    @Column(name = "ID_PARAMETRO")
    private Long idParametro;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "UNIDAD", length = 20)
    private String unidad;

    @OneToMany(mappedBy = "parametro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Medicion> mediciones;
}