package com.monitoreo.proyecto.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sensores")
public class Sensores {

    @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "SEQ_SENSORES")
    @SequenceGenerator(name="SEQ_SENSORES", sequenceName = "SEQ_SENSORES", allocationSize = 1)
@Column(name = "id_sensor")
private Long id_sensor;
@Column(name = "codigo_sensor")
private String codigo_sensor;;
@Column(name = "ubicacion",columnDefinition = "Number(10,3)")
private Double ubicacion;

@ManyToOne
@JoinColumn(name = "id_rio")
private Rio rio;

@OneToMany(mappedBy = "sensores")
@JsonIgnoreProperties("sensores")
private List<Muestra> muestra;

public Sensores() {
}

public Sensores(Long id_sensor, String codigo_sensor, Double ubicacion, Rio rio) {
    this.id_sensor = id_sensor;
    this.codigo_sensor = codigo_sensor;
    this.ubicacion = ubicacion;
    this.rio = rio;
}

public Long getId_sensor() {
    return id_sensor;
}

public void setId_sensor(Long id_sensor) {
    this.id_sensor = id_sensor;
}

public String getCodigo_sensor() {
    return codigo_sensor;
}

public void setCodigo_sensor(String codigo_sensor) {
    this.codigo_sensor = codigo_sensor;
}

public Double getUbicacion() {
    return ubicacion;
}

public void setUbicacion(Double ubicacion) {
    this.ubicacion = ubicacion;
}

public Rio getRio() {
    return rio;
}

public void setRio(Rio rio) {
    this.rio = rio;
}

public List<Muestra> getMuestra() {
    return muestra;
}

public void setMuestra(List<Muestra> muestra) {
    this.muestra = muestra;
}





}
