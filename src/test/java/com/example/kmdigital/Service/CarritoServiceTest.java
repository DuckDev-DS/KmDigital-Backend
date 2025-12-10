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

import com.example.kmdigital.Model.Carrito;
import com.example.kmdigital.Model.Carroceria;
import com.example.kmdigital.Model.Categoria;
import com.example.kmdigital.Model.Comuna;
import com.example.kmdigital.Model.Marca;
import com.example.kmdigital.Model.Modelo;
import com.example.kmdigital.Model.Region;
import com.example.kmdigital.Model.RolUsuario;
import com.example.kmdigital.Model.TipoCombustible;
import com.example.kmdigital.Model.Transmision;
import com.example.kmdigital.Model.Usuario;
import com.example.kmdigital.Model.Vehiculo;
import com.example.kmdigital.Repository.CarritoRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
class CarritoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoService carritoService;

    private Usuario createUsuario() {
        Region region = new Region();
        region.setId(1);
        region.setNombre("Metropolitana");

        Comuna comuna = new Comuna();
        comuna.setId(1);
        comuna.setNombre("Santiago");
        comuna.setRegion(region);

        RolUsuario rol = new RolUsuario();
        rol.setId(1);
        rol.setNombre("Cliente");

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Tester User");
        usuario.setCorreo("user@test.com");
        usuario.setContrasena("123456");
        usuario.setTelefono("+56911111111");
        usuario.setComuna(comuna);
        usuario.setRolUsuario(rol);

        return usuario;
    }

    private Vehiculo createVehiculo() {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Toyota");

        Modelo modelo = new Modelo();
        modelo.setId(1);
        modelo.setNombre("Corolla");
        modelo.setMarca(marca);

        Carroceria carroceria = new Carroceria();
        carroceria.setId(1);
        carroceria.setNombre("Sedán");

        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Auto");

        TipoCombustible combustible = new TipoCombustible();
        combustible.setId(1);
        combustible.setNombre("Bencina");

        Transmision transmision = new Transmision();
        transmision.setId(1);
        transmision.setNombre("Automática");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setNombre("Corolla 2020");
        vehiculo.setPrecio(1250000.0);
        vehiculo.setAnio(2021);
        vehiculo.setDescripcion("descripcion");
        vehiculo.setEstadoVenta("Disponible");
        vehiculo.setImagenAuto("imagenAuto");
        vehiculo.setKilometraje(0);
        vehiculo.setModelo(modelo);
        vehiculo.setCarroceria(carroceria);
        vehiculo.setCategoria(categoria);
        vehiculo.setTransmision(transmision);
        vehiculo.setTipoCombustible(combustible);

        return vehiculo;
    }

    private Carrito createCarrito() {
        Carrito carrito = new Carrito();
        carrito.setId(1);
        carrito.setUsuario(createUsuario());
        carrito.setVehiculo(createVehiculo());
        return carrito;
    }

    
    @Test
    void testSave() {
        Carrito carrito = createCarrito();
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        Carrito saved = carritoService.save(carrito);

        assertNotNull(saved);
        assertNotNull(saved.getUsuario());
        assertNotNull(saved.getVehiculo());
        verify(carritoRepository).save(carrito);
    }

    @Test
    void testFindAll() {
        Carrito carrito = createCarrito();
        when(carritoRepository.findAll()).thenReturn(Collections.singletonList(carrito));

        List<Carrito> carritos = carritoService.findAll();

        assertEquals(1, carritos.size());
        verify(carritoRepository).findAll();
    }

    @Test
    void testFindById() {
        Carrito carrito = createCarrito();
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));

        Carrito found = carritoService.findById(1);

        assertNotNull(found);
        assertEquals(carrito.getId(), found.getId());
        verify(carritoRepository).findById(1);
    }

    @Test
    void testDelete() {
        doNothing().when(carritoRepository).deleteById(1);

        carritoService.deleteById(1);

        verify(carritoRepository).deleteById(1);
    }
}