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

import com.example.kmdigital.Model.Transmision;
import com.example.kmdigital.Service.TransmisionService;



@RestController
@RequestMapping("/api/v1/transmisiones")
public class TransmisionController {
    @Autowired
    private TransmisionService transmisionService;

    @GetMapping()
    public ResponseEntity<List<Transmision>> getAllTransmisiones (){
        List<Transmision> transmision = transmisionService.findAll();
        if (transmision.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transmision);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transmision> getTransmisionById (@PathVariable Integer id){
        Transmision transmision = transmisionService.findById(id);
        if (transmision == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transmision);
    }

    @PostMapping()
    public ResponseEntity<Transmision> createTransmision(@RequestBody Transmision transmision){
        Transmision newTransmision = transmisionService.save(transmision);
        return ResponseEntity.status(201).body(newTransmision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transmision> updateTransmision (@PathVariable Integer id, @RequestBody Transmision transmision){
        try {
            transmision.setId(id);
            Transmision updatedTransmision = transmisionService.update(transmision);
            if (updatedTransmision == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedTransmision);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Transmision> partialUpdateTransmision (@PathVariable Integer id, @RequestBody Transmision transmision){
        try {
            transmision.setId(id);
            Transmision updatedTransmision = transmisionService.partialUpdate(transmision);
            if (updatedTransmision == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedTransmision);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransmision (@PathVariable Integer id){
        try {
            Transmision transmision = transmisionService.findById(id);
            if (transmision== null) {
                return ResponseEntity.notFound().build();
            }
            transmisionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
