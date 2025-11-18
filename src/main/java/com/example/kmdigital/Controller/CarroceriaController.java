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

import com.example.kmdigital.Model.Carroceria;
import com.example.kmdigital.Service.CarroceriaService;


@RestController
@RequestMapping("/api/v1/carrocerias")
public class CarroceriaController {

    @Autowired
    private CarroceriaService carroceriaService;

    @GetMapping
    public ResponseEntity<List<Carroceria>> getAllCarrocerias (){
        List <Carroceria> carrocerias = carroceriaService.findAll();
        if (carrocerias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carrocerias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carroceria> getCarroceriaById (@PathVariable Integer id){
        Carroceria carroceria = carroceriaService.findById(id);
        if (carroceria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carroceria);
    }

    @PostMapping
    public ResponseEntity<Carroceria> createCarroceria (@RequestBody Carroceria carroceria){
        Carroceria newCarroceria = carroceriaService.save(carroceria);
        return ResponseEntity.status(201).body(newCarroceria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carroceria> updateCarroceria (@PathVariable Integer id, @RequestBody Carroceria carroceria){
        try {
            carroceria.setId(id);
            Carroceria updatedCarroceria = carroceriaService.update(carroceria);
            if (updatedCarroceria == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCarroceria);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carroceria> partialUpdateCarroceria (@PathVariable Integer id, @RequestBody Carroceria carroceria){
        try {
            carroceria.setId(id);
            Carroceria updatedCarroceria = carroceriaService.partialUpdate(carroceria);
            if (updatedCarroceria == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCarroceria);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarroceria (@PathVariable Integer id){
        try {
            Carroceria carroceria = carroceriaService.findById(id);
            if (carroceria == null) {
                return ResponseEntity.notFound().build();
            }
            carroceriaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
