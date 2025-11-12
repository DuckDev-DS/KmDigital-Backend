package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Carroceria;
import com.example.kmdigital.Repository.CarroceriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CarroceriaService {

    @Autowired
    private CarroceriaRepository carroceriaRepository;

    public List<Carroceria> findAll() {
        return carroceriaRepository.findAll();
    }

    public Carroceria findById(Integer id) {
        Carroceria carroceria = carroceriaRepository.findById(id).orElse(null);
        return carroceria;
    }

    public Carroceria save(Carroceria carroceria) {
        return carroceriaRepository.save(carroceria);
    }

    public Carroceria update(Carroceria carroceria) {
        return carroceriaRepository.save(carroceria);
    }

    public Carroceria partialUpdate(Carroceria carroceria){
        Carroceria existingCarroceria = carroceriaRepository.findById(carroceria.getId()).orElse(null);
        if (existingCarroceria != null) {
            if (carroceria.getNombre() != null) {
                existingCarroceria.setNombre(carroceria.getNombre());
            }
            return carroceriaRepository.save(existingCarroceria);
        }
        return null;
    }

    public void deleteById(Integer id) {
        carroceriaRepository.deleteById(id);
    }
}
