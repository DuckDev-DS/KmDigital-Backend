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

import com.example.kmdigital.Model.Region;
import com.example.kmdigital.Service.RegionService;


@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping()
    public ResponseEntity<List<Region>> getAllRegiones (){
        List<Region> region = regionService.findAll();
        if (region.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(region);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById (@PathVariable Integer id){
        Region region = regionService.findById(id);
        if (regionService == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(region);
    }

    @PostMapping()
    public ResponseEntity<Region> createRegion (@RequestBody Region region){
        Region newRegion = regionService.save(region);
        return ResponseEntity.status(201).body(newRegion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion (@PathVariable Integer id, @RequestBody Region region){
        try {
            region.setId(id);
            Region updatedRegion = regionService.update(region);
            if (updatedRegion == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedRegion);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> partialUpdateRegion (@PathVariable Integer id, @RequestBody Region region){
        try {
            region.setId(id);
            Region updatedRegion = regionService.partialUpdate(region);
            if (updatedRegion == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedRegion);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion (@PathVariable Integer id){
        try {
            Region region = regionService.findById(id);
            if (region == null) {
                return ResponseEntity.notFound().build();
            }
            regionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
