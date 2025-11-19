package com.example.kmdigital.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombreVehiculo", nullable=false, length=100)
    private String nombre;

    @Column(name="descripcionVehiculo", nullable=false, length=200)
    private String descripcion;

    @Column(name="precioVehiculo", nullable=false)
    private Double precio;

    @Column(name="anioVehiculo", nullable=false)
    private Integer anio;

    @Column(name="kilometrajeVehiculo", nullable=false)
    private Integer kilometraje;

    @Column(name="estadoVenta", nullable=false, length=50)
    private String estadoVenta;

    @Column(name="imagenAuto", nullable=true, length=320)
    private String imagenAuto;

    @ManyToOne
    @JoinColumn(name="carroceria_id")
    private Carroceria carroceria;

    @ManyToOne
    @JoinColumn(name="modelo_id")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="pais_origen_id")
    private PaisOrigen paisOrigen;

    @ManyToOne
    @JoinColumn(name="tipo_combustible_id")
    private TipoCombustible tipoCombustible;

    @ManyToOne
    @JoinColumn(name="sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name="transmision_id")
    private Transmision transmision;
}
