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

import com.example.kmdigital.Model.Sucursal;
import com.example.kmdigital.Service.SucursalService;


@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @GetMapping()
    public ResponseEntity<List<Sucursal>> getAllsucursales (){
        List<Sucursal> sucursal = sucursalService.findAll();
        if (sucursal.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById (@PathVariable Integer id){
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursal);
    }

    @PostMapping()
    public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal){
        Sucursal newSucursal = sucursalService.save(sucursal);
        return ResponseEntity.status(201).body(newSucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal (@PathVariable Integer id, @RequestBody Sucursal sucursal){
        try {
            sucursal.setId(id);
            Sucursal updatedSucursal = sucursalService.update(sucursal);
            if (updatedSucursal == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedSucursal);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sucursal> partialUpdateSucursal (@PathVariable Integer id, @RequestBody Sucursal sucursal){
        try {
            sucursal.setId(id);
            Sucursal updatedSucursal = sucursalService.partialUpdate(sucursal);
            if (updatedSucursal == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedSucursal);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal (@PathVariable Integer id){
        try {
            Sucursal sucursal = sucursalService.findById(id);
            if (sucursal== null) {
                return ResponseEntity.notFound().build();
            }
            sucursalService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
