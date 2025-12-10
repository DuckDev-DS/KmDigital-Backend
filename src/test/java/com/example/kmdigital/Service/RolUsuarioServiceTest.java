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

import com.example.kmdigital.Model.RolUsuario;
import com.example.kmdigital.Repository.RolUsuarioRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class RolUsuarioServiceTest {

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @InjectMocks
    private RolUsuarioService rolUsuarioService;

    private RolUsuario createEntity() {
        RolUsuario r = new RolUsuario();
        r.setId(1);
        r.setNombre("ADMIN");
        return r;
    }

    @Test
    void testSave() {
        RolUsuario rolUsuario = createEntity();
        when(rolUsuarioRepository.save(any(RolUsuario.class))).thenReturn(rolUsuario);

        RolUsuario saved = rolUsuarioService.save(rolUsuario);

        assertNotNull(saved);
        assertEquals("ADMIN", saved.getNombre());
        verify(rolUsuarioRepository).save(rolUsuario);
    }

    @Test
    void testFindAll() {
        RolUsuario rolUsuario = createEntity();
        when(rolUsuarioRepository.findAll()).thenReturn(Collections.singletonList(rolUsuario));

        List<RolUsuario> lista = rolUsuarioService.findAll();

        assertEquals(1, lista.size());
        verify(rolUsuarioRepository).findAll();
    }

    @Test
    void testFindById() {
        RolUsuario rolUsuario = createEntity();
        when(rolUsuarioRepository.findById(1)).thenReturn(Optional.of(rolUsuario));

        RolUsuario found = rolUsuarioService.findById(1);

        assertNotNull(found);
        assertEquals("ADMIN", found.getNombre());
        verify(rolUsuarioRepository).findById(1);
    }

    @Test
    void testUpdate() {
        RolUsuario rolUsuario = createEntity();
        when(rolUsuarioRepository.save(rolUsuario)).thenReturn(rolUsuario);

        rolUsuario.setNombre("CLIENTE");
        RolUsuario updated = rolUsuarioService.update(rolUsuario);

        assertEquals("CLIENTE", updated.getNombre());
        verify(rolUsuarioRepository).save(rolUsuario);
    }

/**
 *    @Test
    void testDelete() {
        doNothing().when(rolUsuarioRepository).deleteById(1);

        rolUsuarioService.deleteById(1);

        verify(rolUsuarioRepository).deleteById(1);
    }
 */
}