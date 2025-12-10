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

import com.example.kmdigital.Model.Carroceria;
import com.example.kmdigital.Model.Categoria;
import com.example.kmdigital.Model.Marca;
import com.example.kmdigital.Model.Modelo;
import com.example.kmdigital.Model.PaisOrigen;
import com.example.kmdigital.Model.Sucursal;
import com.example.kmdigital.Model.TipoCombustible;
import com.example.kmdigital.Model.Transmision;
import com.example.kmdigital.Model.Vehiculo;
import com.example.kmdigital.Repository.VehiculoRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class VehiculoServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private VehiculoService vehiculoService;

    private Vehiculo createVehiculo() {
        Carroceria carroceria = new Carroceria();
        carroceria.setId(1);
        carroceria.setNombre("Sedán");

        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Premium");

        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        Modelo modelo = new Modelo();
        modelo.setId(1);
        modelo.setNombre("Corolla");
        modelo.setMarca(marca);

        PaisOrigen pais = new PaisOrigen();
        pais.setId(1);
        pais.setNombre("Japón");

        TipoCombustible combustible = new TipoCombustible();
        combustible.setId(1);
        combustible.setNombre("Gasolina");

        Transmision transmision = new Transmision();
        transmision.setId(1);
        transmision.setNombre("Automática");

        Sucursal sucursal = new Sucursal();
        sucursal.setId(1);
        sucursal.setNombre("Sucursal Centro");
        sucursal.setDireccion("Av. Principal 123");
        sucursal.setTelefono("123456789");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setNombre("Corolla 2021");
        vehiculo.setDescripcion("Auto en excelente estado");
        vehiculo.setPrecio(15000000.0);
        vehiculo.setAnio(2021);
        vehiculo.setKilometraje(30000);
        vehiculo.setEstadoVenta("Disponible");
        vehiculo.setCarroceria(carroceria);
        vehiculo.setModelo(modelo);
        vehiculo.setCategoria(categoria);
        vehiculo.setPaisOrigen(pais);
        vehiculo.setTipoCombustible(combustible);
        vehiculo.setSucursal(sucursal);
        vehiculo.setTransmision(transmision);

        return vehiculo;
    }

    @Test
    void testSave() {
        Vehiculo vehiculo = createVehiculo();
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        Vehiculo saved = vehiculoService.save(vehiculo);

        assertNotNull(saved);
        assertEquals("Corolla 2021", saved.getNombre());
        verify(vehiculoRepository).save(vehiculo);
    }

    @Test
    void testFindAll() {
        Vehiculo vehiculo = createVehiculo();
        when(vehiculoRepository.findAll()).thenReturn(Collections.singletonList(vehiculo));

        List<Vehiculo> lista = vehiculoService.findAll();

        assertEquals(1, lista.size());
        verify(vehiculoRepository).findAll();
    }

    @Test
    void testFindById() {
        Vehiculo vehiculo = createVehiculo();
        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculo));

        Vehiculo found = vehiculoService.findById(1);

        assertNotNull(found);
        assertEquals(vehiculo.getNombre(), found.getNombre());
        verify(vehiculoRepository).findById(1);
    }

    @Test
    void testPartialUpdate() {
        Vehiculo vehiculo = createVehiculo();
        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculo));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        Vehiculo changes = new Vehiculo();
        changes.setId(1);
        changes.setNombre("Nuevo Nombre Vehículo");

        Vehiculo updated = vehiculoService.partialUpdate(changes);

        assertEquals("Nuevo Nombre Vehículo", updated.getNombre());
        assertEquals(vehiculo.getDescripcion(), updated.getDescripcion());
        verify(vehiculoRepository).findById(1);
        verify(vehiculoRepository).save(vehiculo);
    }
}