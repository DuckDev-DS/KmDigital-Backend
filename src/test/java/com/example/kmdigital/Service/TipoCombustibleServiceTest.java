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

import com.example.kmdigital.Model.TipoCombustible;
import com.example.kmdigital.Repository.TipoCombustibleRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class TipoCombustibleServiceTest {

    @Mock
    private TipoCombustibleRepository tipoCombustibleRepository;

    @InjectMocks
    private TipoCombustibleService tipoCombustibleService;

    @Test
    void testSave() {

        TipoCombustible tc = new TipoCombustible();
        tc.setId(1);
        tc.setNombre("Gasolina");

        when(tipoCombustibleRepository.save(any(TipoCombustible.class))).thenReturn(tc);

        TipoCombustible result = tipoCombustibleService.save(tc);

        assertNotNull(result);
        assertEquals("Gasolina", result.getNombre());
        verify(tipoCombustibleRepository).save(tc);
    }

    @Test
    void testFindAll() {

        TipoCombustible tc = new TipoCombustible();
        tc.setId(1);
        tc.setNombre("Gasolina");

        when(tipoCombustibleRepository.findAll()).thenReturn(Collections.singletonList(tc));

        List<TipoCombustible> lista = tipoCombustibleService.findAll();

        assertEquals(1, lista.size());
        verify(tipoCombustibleRepository).findAll();
    }

    @Test
    void testFindById() {

        TipoCombustible tc = new TipoCombustible();
        tc.setId(1);
        tc.setNombre("Gasolina");

        when(tipoCombustibleRepository.findById(1)).thenReturn(Optional.of(tc));

        TipoCombustible result = tipoCombustibleService.findById(1);

        assertNotNull(result);
        assertEquals("Gasolina", result.getNombre());
        verify(tipoCombustibleRepository).findById(1);
    }

    @Test
    void testUpdate() {

        TipoCombustible tc = new TipoCombustible();
        tc.setId(1);
        tc.setNombre("Gasolina");

        when(tipoCombustibleRepository.save(tc)).thenReturn(tc);

        tc.setNombre("Diésel");

        TipoCombustible updated = tipoCombustibleService.update(tc);

        assertEquals("Diésel", updated.getNombre());
        verify(tipoCombustibleRepository).save(tc);
    }

    @Test
    void testDelete() {

        doNothing().when(tipoCombustibleRepository).deleteById(1);

        tipoCombustibleService.deleteById(1);

        verify(tipoCombustibleRepository).deleteById(1);
    }
}
