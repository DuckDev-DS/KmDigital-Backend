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

import com.example.kmdigital.Model.Marca;
import com.example.kmdigital.Service.MarcaService;




@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping()
    public ResponseEntity<List<Marca>> getAllMarcas (){
        List<Marca> marcas = marcaService.findAll();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById (@PathVariable Integer id){
        Marca marca = marcaService.findById(id);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @PostMapping()
    public ResponseEntity<Marca> createMarca (@RequestBody Marca marca){
        Marca newMarca = marcaService.save(marca);
        return ResponseEntity.status(201).body(newMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca (@PathVariable Integer id, @RequestBody Marca marca){
        try {
            marca.setId(id);
            Marca updatedMarca= marcaService.update(marca);
            if (updatedMarca == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedMarca);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Marca> partialUpdateMarca (@PathVariable Integer id, @RequestBody Marca marca){
        try {
            marca.setId(id);
            Marca updatedMarca = marcaService.partialUpdate(marca);
            if (updatedMarca == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedMarca);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca (@PathVariable Integer id){
        try {
            Marca marca = marcaService.findById(id);
            if (marca == null) {
                return ResponseEntity.notFound().build();
            }
            marcaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
