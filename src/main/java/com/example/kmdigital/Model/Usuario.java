package com.example.kmdigital.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreUsuario", nullable = false, length = 100)
    private String nombre;

    @Column(name = "correoUsuario", nullable = false, length = 100, unique = true)
    private String correo;

    @Column(name = "contrasenaUsuario", nullable = false, length = 300)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

    @Column(name = "telefonoUsuario", nullable = false)
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "rolUsuario_id")
    private RolUsuario rolUsuario;
}
