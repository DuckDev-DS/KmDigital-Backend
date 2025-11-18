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

import com.example.kmdigital.Model.TipoCombustible;
import com.example.kmdigital.Service.TipoCombustibleService;



@RestController
@RequestMapping("/api/v1/tiposCombustibles")
public class TipocombustibleController {
    
    @Autowired
    private TipoCombustibleService tipoCombustibleService;

    @GetMapping
    public ResponseEntity<List<TipoCombustible>> getAlltipoCombustibles (){
        List<TipoCombustible> tipoCombustibles = tipoCombustibleService.findAll();
        if (tipoCombustibles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoCombustibles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCombustible> getTipoCombustibleById (@PathVariable Integer id){
        TipoCombustible tipoCombustible = tipoCombustibleService.findById(id);
        if (tipoCombustible == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoCombustible);
    }

    @PostMapping
    public ResponseEntity<TipoCombustible> createTipoCombustible (@RequestBody TipoCombustible tipoCombustible){
        TipoCombustible newTipoCombustible = tipoCombustibleService.save(tipoCombustible);
        return ResponseEntity.status(201).body(newTipoCombustible);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCombustible> updateTipoCombustible (@PathVariable Integer id, @RequestBody TipoCombustible tipoCombustible){
        try {
            tipoCombustible.setId(id);
            TipoCombustible updatedTipoCombustible = tipoCombustibleService.update(tipoCombustible);
            if (updatedTipoCombustible == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedTipoCombustible);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TipoCombustible> partialUpdateTipoCombustible (@PathVariable Integer id, @RequestBody TipoCombustible tipoCombustible){
        try {
            tipoCombustible.setId(id);
            TipoCombustible updatedtipoCombustible = tipoCombustibleService.partialUpdate(tipoCombustible);
            if (updatedtipoCombustible == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedtipoCombustible);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoCombustible (@PathVariable Integer id){
        try {
            TipoCombustible tipoCombustible = tipoCombustibleService.findById(id);
            if (tipoCombustible == null) {
                return ResponseEntity.notFound().build();
            }
            tipoCombustibleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
