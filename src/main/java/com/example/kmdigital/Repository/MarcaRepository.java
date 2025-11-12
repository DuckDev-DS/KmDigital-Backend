package com.example.kmdigital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
