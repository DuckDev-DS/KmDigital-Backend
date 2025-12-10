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

import com.example.kmdigital.Model.Carroceria;
import com.example.kmdigital.Repository.CarroceriaRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class CarroceriaServiceTest {

    @Mock
    private CarroceriaRepository carroceriaRepository;

    @InjectMocks
    private CarroceriaService carroceriaService;

    private Carroceria createCarroceria() {
        Carroceria c = new Carroceria();
        c.setId(1);
        c.setNombre("Sedán");
        return c;
    }

    @Test
    void testSave() {
        Carroceria c = createCarroceria();
        when(carroceriaRepository.save(any(Carroceria.class))).thenReturn(c);

        Carroceria saved = carroceriaService.save(c);

        assertNotNull(saved);
        assertEquals("Sedán", saved.getNombre());
        verify(carroceriaRepository).save(c);
    }

    @Test
    void testFindAll() {
        Carroceria c = createCarroceria();
        when(carroceriaRepository.findAll()).thenReturn(Collections.singletonList(c));

        List<Carroceria> lista = carroceriaService.findAll();

        assertEquals(1, lista.size());
        verify(carroceriaRepository).findAll();
    }

    @Test
    void testFindById() {
        Carroceria c = createCarroceria();
        when(carroceriaRepository.findById(1)).thenReturn(Optional.of(c));

        Carroceria result = carroceriaService.findById(1);

        assertNotNull(result);
        assertEquals("Sedán", result.getNombre());
        verify(carroceriaRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Carroceria c = createCarroceria();
        when(carroceriaRepository.save(c)).thenReturn(c);

        c.setNombre("SUV");
        Carroceria updated = carroceriaService.update(c);

        assertEquals("SUV", updated.getNombre());
        verify(carroceriaRepository).save(c);
    }

    @Test
    void testDelete() {
        doNothing().when(carroceriaRepository).deleteById(1);

        carroceriaService.deleteById(1);

        verify(carroceriaRepository).deleteById(1);
    }
}
