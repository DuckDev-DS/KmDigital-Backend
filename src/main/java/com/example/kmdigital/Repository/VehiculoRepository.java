package com.example.kmdigital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer>    {
    
    @Query("""
            SELECT v FROM Vehiculo v
            WHERE (:precioMin IS NULL OR v.precio >= :precioMin) AND
                (:precioMax IS NULL OR v.precio <= :precioMax)
            """)
    List<Vehiculo> findByFiltroPrecio(
        @Param("precioMin") Double precioMin,
        @Param("precioMax")Double precioMax
    );

    @Query("""
            SELECT v
            FROM Vehiculo v
            JOIN v.modelo Mo
            JOIN Mo.marca Ma
            WHERE Ma.nombre = :nombreMarca
            """)
    List<Vehiculo> findByFiltroMarca(@Param("nombreMarca") String nombreMarca
    );


}
