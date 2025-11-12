package com.example.kmdigital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Carroceria;

@Repository
public interface CarroceriaRepository extends JpaRepository<Carroceria, Integer> {

}
