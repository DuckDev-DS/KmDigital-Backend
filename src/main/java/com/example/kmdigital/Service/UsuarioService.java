package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Carrito;
import com.example.kmdigital.Model.Usuario;
import com.example.kmdigital.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CarritoService carritoService;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }

    public Usuario login(Usuario usuario){
        Usuario foundUsuario = usuarioRepository.findByCorreo(usuario.getCorreo());
        if(foundUsuario != null && passwordEncoder.matches(usuario.getContrasena(), foundUsuario.getContrasena())){
            return foundUsuario;
        }
        return null;
    }

    public Usuario save(Usuario usuario) {
        String passwordHasheada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(passwordHasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return save(usuario);
    }

    public Usuario partialUpdate(Usuario usuario){
        Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existingUsuario != null) {
            if (usuario.getNombre() != null) {
                existingUsuario.setNombre(usuario.getNombre());
            }
            if (usuario.getCorreo() != null) {
                existingUsuario.setCorreo(usuario.getCorreo());
            }
            if (usuario.getContrasena() != null) {
                existingUsuario.setContrasena(usuario.getContrasena());
            }
            if (usuario.getTelefono() != null) {
                existingUsuario.setTelefono(usuario.getTelefono());
            }
            if (usuario.getComuna() != null) {
                existingUsuario.setComuna(usuario.getComuna());
            }
            if (usuario.getRolUsuario() != null) {
                existingUsuario.setRolUsuario(usuario.getRolUsuario());
            }
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    public void deleteById(Integer id) {
        // Eliminar carritos asociados al usuario
        List<Carrito> carritos = carritoService.findAll();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null && carrito.getUsuario().getId().equals(id)) {
                carritoService.deleteById(carrito.getId());
            }
        }
        usuarioRepository.deleteById(id);
    }
}
