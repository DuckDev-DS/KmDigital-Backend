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
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombreSucursal", nullable=false, length=60)
    private String nombre;

    @Column(name="direccionSucursal", nullable=false, length=120)
    private String direccion;

    @Column(name="telefonoSucursal", nullable=false, length=15)
    private String telefono;

    @ManyToOne
    @JoinColumn(name="comuna_id")
    private Comuna comuna;
}
