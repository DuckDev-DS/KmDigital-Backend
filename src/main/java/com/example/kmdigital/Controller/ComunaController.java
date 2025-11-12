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

import com.example.kmdigital.Model.Comuna;
import com.example.kmdigital.Service.ComunaService;


@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping()
    public ResponseEntity<List<Comuna>> getAllComunas (){
        List <Comuna> comunas = comunaService.findAll();
        if (comunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getComunaById (@PathVariable Integer id){
        Comuna comuna = comunaService.findById(id);
        if (comuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comuna);
    }

    @PostMapping()
    public ResponseEntity<Comuna> createComuna (@RequestBody Comuna comuna){
        Comuna newComuna = comunaService.save(comuna);
        return ResponseEntity.status(201).body(newComuna);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> updateComuna (@PathVariable Integer id, @RequestBody Comuna comuna){
        try {
            comuna.setId(id);
            Comuna updatedComuna= comunaService.update(comuna);
            if (updatedComuna == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedComuna);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> partialUpdateComuna (@PathVariable Integer id, @RequestBody Comuna comuna){
        try {
            comuna.setId(id);
            Comuna updatedComuna = comunaService.partialUpdate(comuna);
            if (updatedComuna == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedComuna);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComuna (@PathVariable Integer id){
        try {
            Comuna comuna = comunaService.findById(id);
            if (comuna == null) {
                return ResponseEntity.notFound().build();
            }
            comunaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
