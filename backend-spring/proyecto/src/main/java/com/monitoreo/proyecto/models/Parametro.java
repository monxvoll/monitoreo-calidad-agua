package com.monitoreo.proyecto.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "Parametro")
public class Parametro {

@Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARAMETRO")
     @SequenceGenerator(name = "SEQ_PARAMETRO",sequenceName = "SEQ_PARAMETRO", allocationSize = 1)
@Column(name="id_parametro")
private Long id_parametro;

@Column(name="tipo")
private String tipo;

@Column(name="unidad")
private String unidad;

@OneToMany(mappedBy = "parametro")
@JsonIgnoreProperties("parametro")
private List<Medicion> medicion;

public Parametro() {
}

public Parametro(Long id_parametro, String tipo, String unidad) {
    this.id_parametro = id_parametro;
    this.tipo = tipo;
    this.unidad = unidad;
}

public Long getId_parametro() {
    return id_parametro;
}

public void setId_parametro(Long id_parametro) {
    this.id_parametro = id_parametro;
}

public String getTipo() {
    return tipo;
}

public void setTipo(String tipo) {
    this.tipo = tipo;
}

public String getUnidad() {
    return unidad;
}

public void setUnidad(String unidad) {
    this.unidad = unidad;
}

public List<Medicion> getMedicion() {
    return medicion;
}

public void setMedicion(List<Medicion> medicion) {
    this.medicion = medicion;
}



}
