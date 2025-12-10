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
import com.example.kmdigital.Model.Sucursal;
import com.example.kmdigital.Repository.SucursalRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;



    @InjectMocks
    private SucursalService sucursalService;

    private Region createRegion() {
        Region r = new Region();
        r.setId(1);
        r.setNombre("Metropolitana");
        return r;
    }

    private Comuna createComuna() {
        Comuna c = new Comuna();
        c.setId(1);
        c.setNombre("Ñuñoa");
        c.setRegion(createRegion());
        return c;
    }

    private Sucursal createSucursal() {
        Sucursal s = new Sucursal();
        s.setId(1);
        s.setNombre("Sucursal Plaza Ñuñoa");
        s.setDireccion("Av. Irarrázaval 1234");
        s.setTelefono("987654321");
        s.setComuna(createComuna());
        return s;
    }

    @Test
    void testSave() {
        Sucursal sucursal = createSucursal();
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursal);

        Sucursal saved = sucursalService.save(sucursal);

        assertNotNull(saved);
        assertEquals("Sucursal Plaza Ñuñoa", saved.getNombre());
        verify(sucursalRepository).save(sucursal);
    }

    @Test
    void testFindAll() {
        Sucursal sucursal = createSucursal();
        when(sucursalRepository.findAll()).thenReturn(Collections.singletonList(sucursal));

        List<Sucursal> lista = sucursalService.findAll();

        assertEquals(1, lista.size());
        verify(sucursalRepository).findAll();
    }

    @Test
    void testFindById() {
        Sucursal sucursal = createSucursal();
        when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursal));

        Sucursal found = sucursalService.findById(1);

        assertNotNull(found);
        assertEquals("Sucursal Plaza Ñuñoa", found.getNombre());
        verify(sucursalRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Sucursal sucursal = createSucursal();
        when(sucursalRepository.save(sucursal)).thenReturn(sucursal);

        sucursal.setTelefono("123123123");
        Sucursal updated = sucursalService.update(sucursal);

        assertEquals("123123123", updated.getTelefono());
        verify(sucursalRepository).save(sucursal);
    }

/**
    @Test
    void testDelete() {
        doNothing().when(sucursalRepository).deleteById(1);

        sucursalService.deleteById(1);

        verify(sucursalRepository).deleteById(1);
    }
 */
}