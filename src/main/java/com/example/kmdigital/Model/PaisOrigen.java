package com.example.kmdigital.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaisOrigen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombrePaisOrigen", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name="imagenPaisOrigen", nullable=true, length=320)
    private String imagenPaisOrigen;
}
