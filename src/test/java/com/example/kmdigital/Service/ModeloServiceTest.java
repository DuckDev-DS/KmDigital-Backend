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
import com.example.kmdigital.Model.Modelo;
import com.example.kmdigital.Repository.ModeloRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class ModeloServiceTest {

    @Mock
    private ModeloRepository modeloRepository;

    @InjectMocks
    private ModeloService modeloService;

    private Modelo createModelo() {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        Modelo modelo = new Modelo();
        modelo.setId(1);
        modelo.setNombre("Corolla");
        modelo.setMarca(marca);

        return modelo;
    }

    @Test
    void testSave() {
        Modelo modelo = createModelo();

        when(modeloRepository.save(any(Modelo.class))).thenReturn(modelo);

        Modelo saved = modeloService.save(modelo);

        assertNotNull(saved);
        assertEquals("Corolla", saved.getNombre());
        verify(modeloRepository).save(modelo);
    }

    @Test
    void testFindAll() {
        Modelo modelo = createModelo();

        when(modeloRepository.findAll()).thenReturn(Collections.singletonList(modelo));

        List<Modelo> lista = modeloService.findAll();

        assertEquals(1, lista.size());
        verify(modeloRepository).findAll();
    }

    @Test
    void testFindById() {
        Modelo modelo = createModelo();

        when(modeloRepository.findById(1)).thenReturn(Optional.of(modelo));

        Modelo found = modeloService.findById(1);

        assertNotNull(found);
        assertEquals("Corolla", found.getNombre());
        verify(modeloRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Modelo modelo = createModelo();

        when(modeloRepository.save(modelo)).thenReturn(modelo);

        modelo.setNombre("Yaris");

        Modelo updated = modeloService.update(modelo);

        assertEquals("Yaris", updated.getNombre());
        verify(modeloRepository).save(modelo);
    }

    @Test
    void testDelete() {
        doNothing().when(modeloRepository).deleteById(1);

        modeloService.deleteById(1);

        verify(modeloRepository).deleteById(1);
    }
}
