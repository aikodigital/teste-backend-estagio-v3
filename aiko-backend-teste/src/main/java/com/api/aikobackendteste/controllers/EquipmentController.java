package com.api.aikobackendteste.controllers;

import com.api.aikobackendteste.models.EquipmentModel;
import com.api.aikobackendteste.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<EquipmentModel> createEquipment(@RequestBody EquipmentModel equipment) {
        EquipmentModel savedEquipment = equipmentService.save(equipment);
        return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentModel> getEquipmentById(@PathVariable UUID id) {
        EquipmentModel equipment = equipmentService.get(id);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentModel>> getAllEquipments() {
        List<EquipmentModel> equipments = equipmentService.getAll();
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentModel> updateEquipment(@PathVariable UUID id, @RequestBody EquipmentModel equipment) {
        EquipmentModel updatedEquipment = equipmentService.update(id, equipment);
        return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable UUID id) {
        String message = equipmentService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}