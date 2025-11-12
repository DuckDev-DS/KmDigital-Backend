package com.example.kmdigital.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Sucursal;
import com.example.kmdigital.Repository.SucursalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private VehiculoService vehiculoService;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal findById(Integer id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        return sucursal;
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal update(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal partialUpdate(Sucursal sucursal){
        Sucursal existingSucursal = sucursalRepository.findById(sucursal.getId()).orElse(null);
        if (existingSucursal != null) {
            if (sucursal.getNombre() != null) {
                existingSucursal.setNombre(sucursal.getNombre());
            }
            if (sucursal.getDireccion() != null) {
                existingSucursal.setDireccion(sucursal.getDireccion());
            }
            if (sucursal.getTelefono() != null) {
                existingSucursal.setTelefono(sucursal.getTelefono());
            }
            if (sucursal.getComuna() != null) {
                existingSucursal.setComuna(sucursal.getComuna());
            }
            return sucursalRepository.save(existingSucursal);
        }
        return null;
    }

    public void deleteById(Integer id) {
        // Eliminar vehículos asociados a la sucursal
        vehiculoService.findAll().forEach(vehiculo -> {
            if (vehiculo.getSucursal() != null && vehiculo.getSucursal().getId().equals(id)) {
                vehiculoService.deleteById(vehiculo.getId());
            }
        });
        sucursalRepository.deleteById(id);
    }
}
