package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Carrito;
import com.example.kmdigital.Model.Vehiculo;
import com.example.kmdigital.Repository.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private CarritoService carritoService;

    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo findById(Integer id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        return vehiculo;
    }

    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo update(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo partialUpdate(Vehiculo vehiculo){
        Vehiculo existingVehiculo = vehiculoRepository.findById(vehiculo.getId()).orElse(null);
        if (existingVehiculo != null) {
            if (vehiculo.getNombre() != null) {
                existingVehiculo.setNombre(vehiculo.getNombre());
            }
            if (vehiculo.getDescripcion() != null) {
                existingVehiculo.setDescripcion(vehiculo.getDescripcion());
            }
            if (vehiculo.getPrecio() != null) {
                existingVehiculo.setPrecio(vehiculo.getPrecio());
            }
            if (vehiculo.getAnio() != null) {
                existingVehiculo.setAnio(vehiculo.getAnio());
            }
            if (vehiculo.getKilometraje() != null) {
                existingVehiculo.setKilometraje(vehiculo.getKilometraje());
            }
            if (vehiculo.getEstadoVenta() != null) {
                existingVehiculo.setEstadoVenta(vehiculo.getEstadoVenta());
            }
            if (vehiculo.getCarroceria() != null) {
                existingVehiculo.setCarroceria(vehiculo.getCarroceria());
            }
            if (vehiculo.getModelo() != null) {
                existingVehiculo.setModelo(vehiculo.getModelo());
            }
            if (vehiculo.getCategoria() != null) {
                existingVehiculo.setCategoria(vehiculo.getCategoria());
            }
            if (vehiculo.getPaisOrigen() != null) {
                existingVehiculo.setPaisOrigen(vehiculo.getPaisOrigen());
            }
            if (vehiculo.getTipoCombustible() != null) {
                existingVehiculo.setTipoCombustible(vehiculo.getTipoCombustible());
            }
            if (vehiculo.getSucursal() != null) {
                existingVehiculo.setSucursal(vehiculo.getSucursal());
            }
            return vehiculoRepository.save(existingVehiculo);
        }
        return null;
    }


    public List<Vehiculo> findByFiltroPrecio(
        Double precioMin,
        Double precioMax
    ) {
        if (precioMin == null && precioMax == null) {
            return vehiculoRepository.findAll();
        }

        if(precioMin < 0 || precioMax < 0) {
            throw new IllegalArgumentException("Los precios no pueden ser negativo");
        }

        return vehiculoRepository.findByFiltroPrecio(
            precioMin,
            precioMax
        );
    }

    public List<Vehiculo> findByFiltroMarca(
        String nombreMarca
    ) {
        if (nombreMarca == null || nombreMarca.isEmpty()) {
            return vehiculoRepository.findAll();
        }

        return vehiculoRepository.findByFiltroMarca(
            nombreMarca
        );
    }

    
    public void deleteById(Integer id) {
        // Eliminar carritos asociados al vehículo
        List<Carrito> carritos = carritoService.findAll();
        for (Carrito carrito : carritos) {
            if (carrito.getVehiculo() != null && carrito.getVehiculo().getId().equals(id)) {
                carritoService.deleteById(carrito.getId());
            }
        }
        vehiculoRepository.deleteById(id);
    }
}