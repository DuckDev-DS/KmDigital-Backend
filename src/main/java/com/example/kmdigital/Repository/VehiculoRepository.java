package com.example.kmdigital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer>    {

    @Query("""
            SELECT V FROM Vehiculo V
            WHERE (:precioMin IS NULL OR v.precio >= :precioMin) AND
                (:precioMax IS NULL OR v.precio <= :precioMax)
            """)
    List<Vehiculo> findByFiltroPrecio(
        Double precioMin,
        Double precioMax
    );

    @Query("""
            SELECT V
            FROM Vehiculo V
            JOIN V.modelo Mo
            JOIN Mo.marca Ma
            WHERE Ma.nombre = :nombreMarca
            """)
    List<Vehiculo> findByFiltroMarca(
        String nombreMarca
    );


}
