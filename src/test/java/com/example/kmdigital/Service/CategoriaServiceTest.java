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

import com.example.kmdigital.Model.Categoria;
import com.example.kmdigital.Repository.CategoriaRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria createCategoria() {
        Categoria c = new Categoria();
        c.setId(1);
        c.setNombre("SUV");
        return c;
    }

    @Test
    void testSave() {
        Categoria categoria = createCategoria();
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria saved = categoriaService.save(categoria);

        assertNotNull(saved);
        assertEquals("SUV", saved.getNombre());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void testFindAll() {
        Categoria categoria = createCategoria();
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));

        List<Categoria> lista = categoriaService.findAll();

        assertEquals(1, lista.size());
        verify(categoriaRepository).findAll();
    }

    @Test
    void testFindById() {
        Categoria categoria = createCategoria();
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        Categoria found = categoriaService.findById(1);

        assertNotNull(found);
        assertEquals("SUV", found.getNombre());
        verify(categoriaRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Categoria categoria = createCategoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        categoria.setNombre("Pick-up");
        Categoria updated = categoriaService.update(categoria);

        assertEquals("Pick-up", updated.getNombre());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void testDelete() {
        doNothing().when(categoriaRepository).deleteById(1);

        categoriaService.deleteById(1);

        verify(categoriaRepository).deleteById(1);
    }
}