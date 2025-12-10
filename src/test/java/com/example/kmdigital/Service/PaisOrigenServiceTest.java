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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kmdigital.Model.PaisOrigen;
import com.example.kmdigital.Repository.PaisOrigenRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class PaisOrigenServiceTest {

    @Mock
    private PaisOrigenRepository paisOrigenRepository;

    @InjectMocks
    private PaisOrigenService paisOrigenService;

    private PaisOrigen createEntity() {
        PaisOrigen p = new PaisOrigen();
        p.setId(1);
        p.setNombre("Japón");
        return p;
    }

    @Test
    void testSave() {
        PaisOrigen paisOrigen = createEntity();
        when(paisOrigenRepository.save(any(PaisOrigen.class))).thenReturn(paisOrigen);

        PaisOrigen saved = paisOrigenService.save(paisOrigen);

        assertNotNull(saved);
        assertEquals("Japón", saved.getNombre());
        verify(paisOrigenRepository).save(paisOrigen);
    }

    @Test
    void testFindAll() {
        PaisOrigen paisOrigen = createEntity();
        when(paisOrigenRepository.findAll()).thenReturn(Collections.singletonList(paisOrigen));

        List<PaisOrigen> lista = paisOrigenService.findAll();

        assertEquals(1, lista.size());
        verify(paisOrigenRepository).findAll();
    }

    @Test
    void testFindById() {
        PaisOrigen paisOrigen = createEntity();
        when(paisOrigenRepository.findById(1)).thenReturn(Optional.of(paisOrigen));

        PaisOrigen found = paisOrigenService.findById(1);

        assertNotNull(found);
        assertEquals("Japón", found.getNombre());
        verify(paisOrigenRepository).findById(1);
    }

    @Test
    void testUpdate() {
        PaisOrigen paisOrigen = createEntity();
        when(paisOrigenRepository.save(paisOrigen)).thenReturn(paisOrigen);

        paisOrigen.setNombre("Alemania");
        PaisOrigen updated = paisOrigenService.update(paisOrigen);

        assertEquals("Alemania", updated.getNombre());
        verify(paisOrigenRepository).save(paisOrigen);
    }

    @Test
    void testDelete() {
        doNothing().when(paisOrigenRepository).deleteById(1);

        paisOrigenService.deleteById(1);

        verify(paisOrigenRepository).deleteById(1);
    }
}