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
@Table(name="Rio") 
public class Rio {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "SEQ_RIO")
    @SequenceGenerator(name="SEQ_RIO", sequenceName = "SEQ_RIO", allocationSize = 1)
@Column(name = "id_rio")
private Long id_rio;
@Column(name = "nombre")
private String nombre;
@Column(name = "longitud",columnDefinition = "Number(10,3)")
private Double longitud;

@OneToMany(mappedBy = "rio")
@JsonIgnoreProperties("rio")
private List<Sensores> sensores;

public Rio() {
}

public Rio(Long id_rio, String nombre, Double longitud) {
    this.id_rio = id_rio;
    this.nombre = nombre;
    this.longitud = longitud;
}

public Long getId_rio() {
    return id_rio;
}

public void setId_rio(Long id_rio) {
    this.id_rio = id_rio;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public Double getLongitud() {
    return longitud;
}

public void setLongitud(Double longitud) {
    this.longitud = longitud;
}

public List<Sensores> getSensores() {
    return sensores;
}

public void setSensores(List<Sensores> sensores) {
    this.sensores = sensores;
}


}
