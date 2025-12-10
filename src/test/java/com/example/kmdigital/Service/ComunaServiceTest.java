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

import com.example.kmdigital.Model.Comuna;
import com.example.kmdigital.Model.Region;
import com.example.kmdigital.Repository.ComunaRepository;


@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class ComunaServiceTest {

    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private ComunaService comunaService;

    private Region createRegion() {
        Region region = new Region();
        region.setId(1);
        region.setNombre("Metropolitana");
        return region;
    }

    private Comuna createComuna() {
        Region region = createRegion();
        Comuna comuna = new Comuna();
        comuna.setId(1);
        comuna.setNombre("Santiago Centro");
        comuna.setRegion(region);
        return comuna;
    }

    @Test
    void testSave() {
        Comuna comuna = createComuna();
        when(comunaRepository.save(any(Comuna.class))).thenReturn(comuna);

        Comuna saved = comunaService.save(comuna);

        assertNotNull(saved);
        assertEquals("Santiago Centro", saved.getNombre());
        verify(comunaRepository).save(comuna);
    }

    @Test
    void testFindAll() {
        Comuna comuna = createComuna();
        when(comunaRepository.findAll()).thenReturn(Collections.singletonList(comuna));

        List<Comuna> lista = comunaService.findAll();

        assertEquals(1, lista.size());
        verify(comunaRepository).findAll();
    }

    @Test
    void testFindById() {
        Comuna comuna = createComuna();
        when(comunaRepository.findById(1)).thenReturn(Optional.of(comuna));

        Comuna found = comunaService.findById(1);

        assertNotNull(found);
        assertEquals("Santiago Centro", found.getNombre());
        verify(comunaRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Comuna comuna = createComuna();
        when(comunaRepository.save(comuna)).thenReturn(comuna);

        comuna.setNombre("Providencia");
        Comuna updated = comunaService.update(comuna);

        assertEquals("Providencia", updated.getNombre());
        verify(comunaRepository).save(comuna);
    }

/**
 *     @Test
    void testDelete() {
        doNothing().when(comunaRepository).deleteById(1);

        comunaService.deleteById(1);

        verify(comunaRepository).deleteById(1);
    }
 */
}