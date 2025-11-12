package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Modelo;
import com.example.kmdigital.Repository.ModeloRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public Modelo findById(Integer id) {
        Modelo modelo = modeloRepository.findById(id).orElse(null);
        return modelo;
    }

    public Modelo save(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Modelo update(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Modelo partialUpdate(Modelo modelo){
        Modelo existingModelo = modeloRepository.findById(modelo.getId()).orElse(null);
        if (existingModelo != null) {
            if (modelo.getNombre() != null) {
                existingModelo.setNombre(modelo.getNombre());
            }
            if (modelo.getMarca() != null) {
                existingModelo.setMarca(modelo.getMarca());
            }
            return modeloRepository.save(existingModelo);
        }
        return null;
    }

    public void deleteById(Integer id) {
        modeloRepository.deleteById(id);
    }
}
