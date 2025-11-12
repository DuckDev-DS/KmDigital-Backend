package com.example.kmdigital.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kmdigital.Model.Carrito;
import com.example.kmdigital.Repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Carrito findById(Integer id) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        return carrito;
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito update(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito partialUpdate(Carrito carrito){
        Carrito existingCarrito = carritoRepository.findById(carrito.getId()).orElse(null);
        if (existingCarrito != null) {
            if (carrito.getUsuario() != null) {
                existingCarrito.setUsuario(carrito.getUsuario());
            }
            if (carrito.getVehiculo() != null) {
                existingCarrito.setVehiculo(carrito.getVehiculo());
            }
            return carritoRepository.save(existingCarrito);
        }
        return null;
    }

    public void deleteById(Integer id) {
        carritoRepository.deleteById(id);
    }
}
