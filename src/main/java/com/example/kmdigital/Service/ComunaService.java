package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Comuna;
import com.example.kmdigital.Repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Comuna findById(Integer id) {
        Comuna comuna = comunaRepository.findById(id).orElse(null);
        return comuna;
    }

    public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna update(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna partialUpdate(Comuna comuna){
        Comuna existingComuna = comunaRepository.findById(comuna.getId()).orElse(null);
        if (existingComuna != null) {
            if (comuna.getNombre() != null) {
                existingComuna.setNombre(comuna.getNombre());
            }
            if (comuna.getRegion() != null) {
                existingComuna.setRegion(comuna.getRegion());
            }
            return comunaRepository.save(existingComuna);
        }
        return null;
    }

    //Si se elimina la comuna se elimina todo lo relacionado a ella, en teoria
    public void deleteById(Integer id) {
        // Se buscan y eliminan las sucursales asociadas a la comuna
        sucursalService.findAll().forEach(sucursal -> {
            if (sucursal.getComuna() != null && sucursal.getComuna().getId().equals(id)) {
                sucursalService.deleteById(sucursal.getId());
            }
        });

        // lo mismo con los usuarios
        usuarioService.findAll().forEach(usuario -> {
            if (usuario.getComuna() != null && usuario.getComuna().getId().equals(id)) {
                usuarioService.deleteById(usuario.getId());
            }
        });

        comunaRepository.deleteById(id);
    }
}
