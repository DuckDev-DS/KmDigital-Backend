package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.PaisOrigen;
import com.example.kmdigital.Repository.PaisOrigenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class PaisOrigenService {

    @Autowired
    private PaisOrigenRepository paisOrigenRepository;

    public List<PaisOrigen> findAll() {
        return paisOrigenRepository.findAll();
    }

    public PaisOrigen findById(Integer id) {
        PaisOrigen paisOrigen = paisOrigenRepository.findById(id).orElse(null);
        return paisOrigen;
    }

    public PaisOrigen save(PaisOrigen paisOrigen) {
        return paisOrigenRepository.save(paisOrigen);
    }

    public PaisOrigen update(PaisOrigen paisOrigen) {
        return paisOrigenRepository.save(paisOrigen);
    }

    public PaisOrigen partialUpdate(PaisOrigen paisOrigen){
        PaisOrigen existingPaisOrigen = paisOrigenRepository.findById(paisOrigen.getId()).orElse(null);
        if (existingPaisOrigen != null) {
            if (paisOrigen.getNombre() != null) {
                existingPaisOrigen.setNombre(paisOrigen.getNombre());
            }
            if (paisOrigen.getImagenPaisOrigen() != null) {
                existingPaisOrigen.setImagenPaisOrigen(paisOrigen.getImagenPaisOrigen());
            }
            return paisOrigenRepository.save(existingPaisOrigen);
        }
        return null;
    }

    public void deleteById(Integer id) {
        paisOrigenRepository.deleteById(id);
    }
}