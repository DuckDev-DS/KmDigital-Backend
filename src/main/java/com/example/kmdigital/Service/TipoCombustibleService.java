package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.TipoCombustible;
import com.example.kmdigital.Repository.TipoCombustibleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class TipoCombustibleService {

    @Autowired
    private TipoCombustibleRepository tipoCombustibleRepository;

    public List<TipoCombustible> findAll() {
        return tipoCombustibleRepository.findAll();
    }

    public TipoCombustible findById(Integer id) {
        TipoCombustible tipoCombustible = tipoCombustibleRepository.findById(id).orElse(null);
        return tipoCombustible;
    }

    public TipoCombustible save(TipoCombustible tipoCombustible) {
        return tipoCombustibleRepository.save(tipoCombustible);
    }

    public TipoCombustible update(TipoCombustible tipoCombustible) {
        return tipoCombustibleRepository.save(tipoCombustible);
    }

    public TipoCombustible partialUpdate(TipoCombustible tipoCombustible){
        TipoCombustible existingTipoCombustible = tipoCombustibleRepository.findById(tipoCombustible.getId()).orElse(null);
        if (existingTipoCombustible != null) {
            if (tipoCombustible.getNombre() != null) {
                existingTipoCombustible.setNombre(tipoCombustible.getNombre());
            }
            return tipoCombustibleRepository.save(existingTipoCombustible);
        }
        return null;
    }

    public void deleteById(Integer id) {
        tipoCombustibleRepository.deleteById(id);
    }
}
