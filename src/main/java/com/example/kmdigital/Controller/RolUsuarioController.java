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

import com.example.kmdigital.Model.RolUsuario;
import com.example.kmdigital.Service.RolUsuarioService;



@RestController
@RequestMapping("/api/v1/rolesUsuario")
public class RolUsuarioController {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @GetMapping
    public ResponseEntity<List<RolUsuario>> getAllRoles (){
        List<RolUsuario> rolUsuario = rolUsuarioService.findAll();
        if (rolUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rolUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolUsuario> getRolUsuarioById (@PathVariable Integer id){
        RolUsuario rolUsuario = rolUsuarioService.findById(id);
        if (rolUsuarioService == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rolUsuario);
    }

    @PostMapping
    public ResponseEntity<RolUsuario> createRolUsuario (@RequestBody RolUsuario rolUsuario){
        RolUsuario newRolUsuario = rolUsuarioService.save(rolUsuario);
        return ResponseEntity.status(201).body(newRolUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolUsuario> updateRolUsuario (@PathVariable Integer id, @RequestBody RolUsuario rolUsuario){
        try {
            rolUsuario.setId(id);
            RolUsuario updatedRolUsuario = rolUsuarioService.update(rolUsuario);
            if (updatedRolUsuario == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedRolUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RolUsuario> partialUpdateRolUsuario (@PathVariable Integer id, @RequestBody RolUsuario rolUsuario){
        try {
            rolUsuario.setId(id);
            RolUsuario updatedRolUsuario = rolUsuarioService.partialUpdate(rolUsuario);
            if (updatedRolUsuario == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedRolUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolUsuario (@PathVariable Integer id){
        try {
            RolUsuario rolUsuario = rolUsuarioService.findById(id);
            if (rolUsuario == null) {
                return ResponseEntity.notFound().build();
            }
            rolUsuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}