package com.example.kmdigital.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.kmdigital.Model.Comuna;
import com.example.kmdigital.Model.Region;
import com.example.kmdigital.Model.RolUsuario;
import com.example.kmdigital.Model.Usuario;
import com.example.kmdigital.Repository.ComunaRepository;
import com.example.kmdigital.Repository.RegionRepository;
import com.example.kmdigital.Repository.RolUsuarioRepository;
import com.example.kmdigital.Repository.UsuarioRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ComunaRepository comunaRepository;

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario createUsuario() {
        Region region = new Region();
        region.setId(1);
        region.setNombre("Metropolitana");

        Comuna comuna = new Comuna();
        comuna.setId(1);
        comuna.setNombre("Santiago");
        comuna.setRegion(region);

        RolUsuario rol = new RolUsuario();
        rol.setId(1);
        rol.setNombre("Cliente");

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Tester");
        usuario.setCorreo("tester@test.com");
        usuario.setContrasena("colocololomasgrande");
        usuario.setTelefono("+56912345678");
        usuario.setComuna(comuna);
        usuario.setRolUsuario(rol);

        return usuario;
    }

    @Test
    void testSave() {
        Usuario usuario = createUsuario();
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);

        assertNotNull(saved);
        assertEquals("Tester", saved.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testFindAll() {
        Usuario usuario = createUsuario();
        when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(usuario));

        List<Usuario> usuarios = usuarioService.findAll();

        assertEquals(1, usuarios.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testFindById() {
        Usuario usuario = createUsuario();
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(1);

        assertNotNull(found);
        assertEquals("Tester", found.getNombre());
        verify(usuarioRepository).findById(1);
    }

    @Test
    void testPartialUpdate() {
        Usuario usuario = createUsuario();
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario changes = new Usuario();
        changes.setId(1);
        changes.setNombre("Nuevo Nombre");

        Usuario updated = usuarioService.partialUpdate(changes);

        assertEquals("Nuevo Nombre", updated.getNombre());
        assertEquals(usuario.getCorreo(), updated.getCorreo());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).save(usuario);
    }
    
/**
    @Test
    void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1);

        usuarioService.deleteById(1);

        verify(usuarioRepository).deleteById(1);
    }

 */

}