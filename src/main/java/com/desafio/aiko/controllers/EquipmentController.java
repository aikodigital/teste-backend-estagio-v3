package com.desafio.aiko.controllers;

import com.desafio.aiko.models.entities.Equipment;
import com.desafio.aiko.models.request.EquipmentRequest;
import com.desafio.aiko.repositories.EquipmentModelRepository;
import com.desafio.aiko.repositories.EquipmentPositionHistoryRepository;
import com.desafio.aiko.repositories.EquipmentRepository;
import com.desafio.aiko.services.EquipmentPositionHistoryService;
import com.desafio.aiko.services.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/equipments")
@Slf4j
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    EquipmentRepository equipmentRepository;

    @PostMapping("/create-equipment")
    public ResponseEntity createEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        try {
            equipmentService.create(equipmentRequest);
            return ResponseEntity.status(201).body("Equipment created!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not create equipment...");
        }


    }

    @DeleteMapping("/delete-equipment")
    public ResponseEntity deleteEquipmentById(@RequestParam UUID uuid) {
        try {
            equipmentService.delete(uuid);
            return ResponseEntity.status(200).body("Equipment deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not delete equipment...");
        }
    }

    @PutMapping("/update-equipment")
    public ResponseEntity updateEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        try {
            equipmentService.updateEquipment(equipmentRequest);
            return ResponseEntity.status(200).body("Equipment updated!!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not update equipment...");
        }
    }


    @GetMapping("/get-equipment/")
    public ResponseEntity findAllEquipments() {
        try {
            List<EquipmentRequest> equipment = equipmentService.findAllEquipments();
            return ResponseEntity.status(200).body(equipment);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not get equipments...");
        }
    }

    @GetMapping("/get-equipment/{uuid}")
    public ResponseEntity findById(@PathVariable UUID uuid) {
        try {
            Optional<Equipment> equipment = equipmentRepository.findById(uuid);

            return ResponseEntity.status(200).body(equipment);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not return equipment...");
        }
    }


}