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

import com.example.kmdigital.Model.Marca;
import com.example.kmdigital.Repository.MarcaRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaService marcaService;

    @Test
    void testSave() {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        when(marcaRepository.save(any(Marca.class))).thenReturn(marca);

        Marca result = marcaService.save(marca);

        assertNotNull(result);
        assertEquals("Toyota", result.getNombre());
        verify(marcaRepository).save(marca);
    }

    @Test
    void testFindAll() {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        when(marcaRepository.findAll()).thenReturn(Collections.singletonList(marca));

        List<Marca> lista = marcaService.findAll();

        assertEquals(1, lista.size());
        verify(marcaRepository).findAll();
    }

    @Test
    void testFindById() {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        when(marcaRepository.findById(1)).thenReturn(Optional.of(marca));

        Marca result = marcaService.findById(1);

        assertNotNull(result);
        assertEquals("Toyota", result.getNombre());
        verify(marcaRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        when(marcaRepository.save(marca)).thenReturn(marca);

        marca.setNombre("Nissan");

        Marca updated = marcaService.update(marca);

        assertEquals("Nissan", updated.getNombre());
        verify(marcaRepository).save(marca);
    }

    @Test
    void testDelete() {
        doNothing().when(marcaRepository).deleteById(1);

        marcaService.deleteById(1);

        verify(marcaRepository).deleteById(1);
    }
}
