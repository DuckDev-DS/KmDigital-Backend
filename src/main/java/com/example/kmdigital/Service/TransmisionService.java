package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Transmision;
import com.example.kmdigital.Repository.TransmisionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class TransmisionService {

    @Autowired
    private TransmisionRepository transmisionRepository;

    public List<Transmision> findAll() {
        return transmisionRepository.findAll();
    }

    public Transmision findById(Integer id) {
        Transmision transmision = transmisionRepository.findById(id).orElse(null);
        return transmision;
    }

    public Transmision save(Transmision transmision) {
        return transmisionRepository.save(transmision);
    }

    public Transmision update(Transmision transmision) {
        return transmisionRepository.save(transmision);
    }

    public Transmision partialUpdate(Transmision transmision){
        Transmision existingTransmision = transmisionRepository.findById(transmision.getId()).orElse(null);
        if (existingTransmision != null) {
            if (transmision.getNombre() != null) {
                existingTransmision.setNombre(transmision.getNombre());
            }
            return transmisionRepository.save(existingTransmision);
        }
        return null;
    }

    public void deleteById(Integer id) {
        transmisionRepository.deleteById(id);
    }
}
