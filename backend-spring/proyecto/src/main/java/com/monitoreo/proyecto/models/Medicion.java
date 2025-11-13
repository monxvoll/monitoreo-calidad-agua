package com.monitoreo.proyecto.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Medicion")
public class Medicion {

 @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEDICION")
     @SequenceGenerator(name = "SEQ_MEDICION",sequenceName = "SEQ_MEDICION", allocationSize = 1)
    @Column(name ="id_medicion")
    private Long id_medicion;
    @Column(name = "valor", columnDefinition = "Number(10,3)")
    private Double valor;

      @JsonIgnoreProperties("medicion")
   @ManyToOne
    @JoinColumn(name ="id_muestra")
    private Muestra muestra;

    
      @JsonIgnoreProperties("medicion")
   @ManyToOne
    @JoinColumn(name ="id_parametro")
    private Parametro parametro;


      public Medicion() {
      }


      public Medicion(Long id_medicion, Double valor, Muestra muestra, Parametro parametro) {
        this.id_medicion = id_medicion;
        this.valor = valor;
        this.muestra = muestra;
        this.parametro = parametro;
      }


      public Long getId_medicion() {
          return id_medicion;
      }


      public void setId_medicion(Long id_medicion) {
          this.id_medicion = id_medicion;
      }


      public Double getValor() {
          return valor;
      }


      public void setValor(Double valor) {
          this.valor = valor;
      }


      public Muestra getMuestra() {
          return muestra;
      }


      public void setMuestra(Muestra muestra) {
          this.muestra = muestra;
      }


      public Parametro getParametro() {
          return parametro;
      }


      public void setParametro(Parametro parametro) {
          this.parametro = parametro;
      }

    

}
