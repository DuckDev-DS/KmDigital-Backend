package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.RolUsuario;
import com.example.kmdigital.Repository.RolUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RolUsuarioService {

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<RolUsuario> findAll() {
        return rolUsuarioRepository.findAll();
    }

    public RolUsuario findById(Integer id) {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(id).orElse(null);
        return rolUsuario;
    }

    public RolUsuario save(RolUsuario rolUsuario) {
        return rolUsuarioRepository.save(rolUsuario);
    }

    public RolUsuario update(RolUsuario rolUsuario) {
        return rolUsuarioRepository.save(rolUsuario);
    }

    public RolUsuario partialUpdate(RolUsuario rolUsuario){
        RolUsuario existingRolUsuario = rolUsuarioRepository.findById(rolUsuario.getId()).orElse(null);
        if (existingRolUsuario != null) {
            if (rolUsuario.getNombre() != null) {
                existingRolUsuario.setNombre(rolUsuario.getNombre());
            }
            return rolUsuarioRepository.save(existingRolUsuario);
        }
        return null;
    }

    public void deleteById(Integer id) {
        // Eliminar usuarios asociados a este rol
        usuarioService.findAll().forEach(usuario -> {
            if (usuario.getRolUsuario() != null && usuario.getRolUsuario().getId().equals(id)) {
                usuarioService.deleteById(usuario.getId());
            }
        });
        rolUsuarioRepository.deleteById(id);
    }
}