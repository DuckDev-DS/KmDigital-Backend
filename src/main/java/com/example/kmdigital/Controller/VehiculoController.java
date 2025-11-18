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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kmdigital.Model.Vehiculo;
import com.example.kmdigital.Service.VehiculoService;



@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos (){
        List<Vehiculo> vehiculo = vehiculoService.findAll();
        if (vehiculo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById (@PathVariable Integer id){
        Vehiculo vehiculo = vehiculoService.findById(id);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }


    @GetMapping("/filtro/precio/{precioMin}/{precioMax}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByFiltroPrecio (
        @RequestParam Double precioMin,
        @RequestParam Double precioMax
    ){
        List<Vehiculo> vehiculos = vehiculoService.findByFiltroPrecio(precioMin, precioMax);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/filtro/marca/{nombreMarca}")
    public ResponseEntity<List<Vehiculo>> getVehiculoMarca (
        @RequestParam String nombreMarca
    ) {
        List<Vehiculo> vehiculos = vehiculoService.findByFiltroMarca(nombreMarca);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }
    
    

    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo){
        Vehiculo newVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.status(201).body(newVehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo (@PathVariable Integer id, @RequestBody Vehiculo vehiculo){
        try {
            vehiculo.setId(id);
            Vehiculo updatedVehiculo = vehiculoService.update(vehiculo);
            if (updatedVehiculo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedVehiculo);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehiculo> partialUpdateVehiculo (@PathVariable Integer id, @RequestBody Vehiculo vehiculo){
        try {
            vehiculo.setId(id);
            Vehiculo updatedVehiculo = vehiculoService.partialUpdate(vehiculo);
            if (updatedVehiculo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedVehiculo);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo (@PathVariable Integer id){
        try {
            Vehiculo vehiculo = vehiculoService.findById(id);
            if (vehiculo == null) {
                return ResponseEntity.notFound().build();
            }
            vehiculoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
