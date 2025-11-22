package com.monitoreo.agua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUDITORIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {

    @Id
    @Column(name = "ID_AUDITORIA")
    private Long idAuditoria;

    @Column(name = "NOMBRE_TABLA")
    private String nombreTabla;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "ID_REGISTRO")
    private Long idRegistro;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "FECHA_OPERACION")
    private LocalDateTime fechaOperacion;

    @Lob
    @Column(name = "DATOS_ANTIGUOS")
    private String datosAntiguos;

    @Lob
    @Column(name = "DATOS_NUEVOS")
    private String datosNuevos;
}