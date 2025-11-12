package com.example.kmdigital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>   {

}
