package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Marca;
import com.example.kmdigital.Repository.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        return marca;
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca update(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca partialUpdate(Marca marca){
        Marca existingMarca = marcaRepository.findById(marca.getId()).orElse(null);
        if (existingMarca != null) {
            if (marca.getNombre() != null) {
                existingMarca.setNombre(marca.getNombre());
            }
            return marcaRepository.save(existingMarca);
        }
        return null;
    }

    public void deleteById(Integer id) {
        marcaRepository.deleteById(id);
    }
}