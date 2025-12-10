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

import com.example.kmdigital.Model.Region;
import com.example.kmdigital.Repository.RegionRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    private Region createRegion() {
        Region r = new Region();
        r.setId(1);
        r.setNombre("Metropolitana");
        return r;
    }

    @Test
    void testSave() {
        Region region = createRegion();
        when(regionRepository.save(any(Region.class))).thenReturn(region);

        Region saved = regionService.save(region);

        assertNotNull(saved);
        assertEquals("Metropolitana", saved.getNombre());
        verify(regionRepository).save(region);
    }

    @Test
    void testFindAll() {
        Region region = createRegion();
        when(regionRepository.findAll()).thenReturn(Collections.singletonList(region));

        List<Region> lista = regionService.findAll();

        assertEquals(1, lista.size());
        verify(regionRepository).findAll();
    }

    @Test
    void testFindById() {
        Region region = createRegion();
        when(regionRepository.findById(1)).thenReturn(Optional.of(region));

        Region found = regionService.findById(1);

        assertNotNull(found);
        assertEquals("Metropolitana", found.getNombre());
        verify(regionRepository).findById(1);
    }

    @Test
    void testUpdate() {
        Region region = createRegion();
        when(regionRepository.save(region)).thenReturn(region);

        region.setNombre("Biobío");
        Region updated = regionService.update(region);

        assertEquals("Biobío", updated.getNombre());
        verify(regionRepository).save(region);
    }

    @Test
    void testDelete() {
        doNothing().when(regionRepository).deleteById(1);

        regionService.deleteById(1);

        verify(regionRepository).deleteById(1);
    }
}