package com.example.kmdigital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

}
