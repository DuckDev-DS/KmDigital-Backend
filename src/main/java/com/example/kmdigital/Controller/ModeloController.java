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

import com.example.kmdigital.Model.Modelo;
import com.example.kmdigital.Service.ModeloService;


@RestController
@RequestMapping("/api/v1/modelos")
public class ModeloController {
    @Autowired
    private ModeloService modeloService;

    @GetMapping()
    public ResponseEntity<List<Modelo>> getAllModelos (){
        List<Modelo> modelos = modeloService.findAll();
        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getModeloById (@PathVariable Integer id){
        Modelo modelo = modeloService.findById(id);
        if (modelo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelo);
    }

    @PostMapping()
    public ResponseEntity<Modelo> createModelo (@RequestBody Modelo modelo){
        Modelo newModelo = modeloService.save(modelo);
        return ResponseEntity.status(201).body(newModelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> updateModelo (@PathVariable Integer id, @RequestBody Modelo modelo){
        try {
            modelo.setId(id);
            Modelo updatedModelo= modeloService.update(modelo);
            if (updatedModelo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedModelo);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Modelo> partialUpdateModelo (@PathVariable Integer id, @RequestBody Modelo modelo){
        try {
            modelo.setId(id);
            Modelo updatedModelo = modeloService.partialUpdate(modelo);
            if (updatedModelo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedModelo);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModelo (@PathVariable Integer id){
        try {
            Modelo modelo = modeloService.findById(id);
            if (modelo == null) {
                return ResponseEntity.notFound().build();
            }
            modeloService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
