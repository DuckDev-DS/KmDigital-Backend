package com.example.kmdigital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.TipoCombustible;

@Repository
public interface TipoCombustibleRepository extends JpaRepository<TipoCombustible, Integer> {

}
