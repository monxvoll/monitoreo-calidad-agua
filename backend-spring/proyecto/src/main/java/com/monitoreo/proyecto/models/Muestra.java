package com.monitoreo.proyecto.models;
import java.time.LocalDateTime;
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
@Table(name = "Muestra")
public class Muestra {
    
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MUESTRA")
     @SequenceGenerator(name = "SEQ_MUESTRA",sequenceName = "SEQ_MUESTRA", allocationSize = 1)
    @Column(name ="id_muestra")
    private Long id_muestra;

    @Column(name ="fecha_muestra")
   private  LocalDateTime fecha_muestra ;

    
   @JsonIgnoreProperties("muestra")
   @ManyToOne
    @JoinColumn(name ="id_sensor")
    private Sensores sensores;

     @OneToMany(mappedBy = "muestra")
    @JsonIgnoreProperties("muestra")
    private List<Medicion> medicion;

     public Muestra() {
     }

     public Muestra(Long id_muestra, LocalDateTime fecha_muestra, Sensores sensores) {
        this.id_muestra = id_muestra;
        this.fecha_muestra = fecha_muestra;
        this.sensores = sensores;
     }

     public Long getId_muestra() {
         return id_muestra;
     }

     public void setId_muestra(Long id_muestra) {
         this.id_muestra = id_muestra;
     }

     public LocalDateTime getFecha_muestra() {
         return fecha_muestra;
     }

     public void setFecha_muestra(LocalDateTime fecha_muestra) {
         this.fecha_muestra = fecha_muestra;
     }

     public Sensores getSensores() {
         return sensores;
     }

     public void setSensores(Sensores sensores) {
         this.sensores = sensores;
     }

     public List<Medicion> getMedicion() {
         return medicion;
     }

     public void setMedicion(List<Medicion> medicion) {
         this.medicion = medicion;
     }

     

}
