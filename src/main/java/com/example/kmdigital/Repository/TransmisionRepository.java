package com.example.kmdigital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kmdigital.Model.Transmision;

@Repository
public interface TransmisionRepository extends JpaRepository<Transmision, Integer> {

}
