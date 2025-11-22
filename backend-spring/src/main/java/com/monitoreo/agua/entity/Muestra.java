package com.monitoreo.agua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MUESTRA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Muestra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_muestra")
    @SequenceGenerator(name = "seq_muestra", sequenceName = "SEQ_MUESTRA", allocationSize = 1)
    @Column(name = "ID_MUESTRA")
    private Long idMuestra;

    @Column(name = "FECHA_MUESTRA")
    private LocalDateTime fechaMuestra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SENSOR")
    @JsonIgnore
    private Sensor sensor;

    @OneToMany(mappedBy = "muestra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicion> mediciones;
}