package com.example.kmdigital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kmdigital.Model.Carrito;
import com.example.kmdigital.Service.CarritoService;


@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> getAllCarritos (){
        List <Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById (@PathVariable Integer id){
        Carrito carrito = carritoService.findById(id);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<Carrito> createCarrito (@RequestBody Carrito carrito){
        Carrito newCarrito = carritoService.save(carrito);
        return ResponseEntity.status(201).body(newCarrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> updateCarrito (@PathVariable Integer id, @RequestBody Carrito carrito){
        try {
            carrito.setId(id);
            Carrito updatedCarrito = carritoService.update(carrito);
            if (updatedCarrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCarrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carrito> partialUpdateCarrito (@PathVariable Integer id, @RequestBody Carrito carrito){
        try {
            carrito.setId(id);
            Carrito updatedCarrito = carritoService.partialUpdate(carrito);
            if (updatedCarrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCarrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito (@PathVariable Integer id){
        try {
            Carrito carrito = carritoService.findById(id);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            carritoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
