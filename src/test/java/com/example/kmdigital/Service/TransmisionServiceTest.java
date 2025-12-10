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

import com.example.kmdigital.Model.Transmision;
import com.example.kmdigital.Repository.TransmisionRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class TransmisionServiceTest {

    @Mock
    private TransmisionRepository transmisionRepository;

    @InjectMocks
    private TransmisionService transmisionService;

    private Transmision createTransmision() {
        Transmision t = new Transmision();
        t.setId(1);
        t.setNombre("Automática");
        return t;
    }

    @Test
    void testSave() {
        Transmision t = createTransmision();
        when(transmisionRepository.save(any(Transmision.class))).thenReturn(t);

        Transmision saved = transmisionService.save(t);

        assertNotNull(saved);
        assertEquals("Automática", saved.getNombre());
        verify(transmisionRepository).save(t);
    }

    @Test
    void testFindAll() {
        Transmision t = createTransmision();
        when(transmisionRepository.findAll()).thenReturn(Collections.singletonList(t));

        List<Transmision> lista = transmisionService.findAll();

        assertEquals(1, lista.size());
        verify(transmisionRepository).findAll();
    }

    @Test
    void testFindById() {
        Transmision t = createTransmision();
        when(transmisionRepository.findById(1)).thenReturn(Optional.of(t));

        Transmision result = transmisionService.findById(1);

        assertNotNull(result);
        assertEquals("Automática", result.getNombre());
        verify(transmisionRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Transmision t = createTransmision();
        when(transmisionRepository.save(t)).thenReturn(t);

        t.setNombre("Manual");
        Transmision updated = transmisionService.update(t);

        assertEquals("Manual", updated.getNombre());
        verify(transmisionRepository).save(t);
    }

    @Test
    void testDelete() {
        doNothing().when(transmisionRepository).deleteById(1);

        transmisionService.deleteById(1);

        verify(transmisionRepository).deleteById(1);
    }
}
