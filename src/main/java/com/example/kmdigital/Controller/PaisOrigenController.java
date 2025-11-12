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

import com.example.kmdigital.Model.PaisOrigen;
import com.example.kmdigital.Service.PaisOrigenService;



@RestController
@RequestMapping("/api/v1/paisesOrigen")
public class PaisOrigenController {
    @Autowired
    private PaisOrigenService paisOrigenService;

    @GetMapping()
    public ResponseEntity<List<PaisOrigen>> getAllPaises (){
        List<PaisOrigen> paisesOrigen = paisOrigenService.findAll();
        if (paisesOrigen.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paisesOrigen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisOrigen> getPaisById (@PathVariable Integer id){
        PaisOrigen paisOrigen = paisOrigenService.findById(id);
        if (paisOrigen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paisOrigen);
    }

    @PostMapping()
    public ResponseEntity<PaisOrigen> createPais (@RequestBody PaisOrigen paisOrigen){
        PaisOrigen newPaisOrigen = paisOrigenService.save(paisOrigen);
        return ResponseEntity.status(201).body(newPaisOrigen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisOrigen> updatePais (@PathVariable Integer id, @RequestBody PaisOrigen paisOrigen){
        try {
            paisOrigen.setId(id);
            PaisOrigen updatedPaisOrigen= paisOrigenService.update(paisOrigen);
            if (updatedPaisOrigen == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedPaisOrigen);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PaisOrigen> partialUpdatePais (@PathVariable Integer id, @RequestBody PaisOrigen paisOrigen){
        try {
            paisOrigen.setId(id);
            PaisOrigen updatedPaisOrigen = paisOrigenService.partialUpdate(paisOrigen);
            if (updatedPaisOrigen == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedPaisOrigen);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePais (@PathVariable Integer id){
        try {
            PaisOrigen paisOrigen = paisOrigenService.findById(id);
            if (paisOrigen == null) {
                return ResponseEntity.notFound().build();
            }
            paisOrigenService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
