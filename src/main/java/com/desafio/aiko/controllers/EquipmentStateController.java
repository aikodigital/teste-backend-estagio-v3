package com.desafio.aiko.controllers;

import com.desafio.aiko.models.request.EquipmentStateRequest;
import com.desafio.aiko.services.EquipmentStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-state")
@Slf4j
public class EquipmentStateController {
    @Autowired
    EquipmentStateService equipmentStateService;

    @PostMapping("/create-state/")
    public ResponseEntity createState(@RequestBody EquipmentStateRequest equipmentStateRequest) {
        try {
            equipmentStateService.create(equipmentStateRequest);
            return ResponseEntity.status(201).body("State created!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not create state...");
        }
    }

    @GetMapping("/get-states/")
    public ResponseEntity getStates() {
        try {
            List<EquipmentStateRequest> equipmentStateList = equipmentStateService.findAllStates();
            return ResponseEntity.status(201).body(equipmentStateList);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Nothing here!");
        }
    }

    @DeleteMapping("/delete-state")
    public ResponseEntity deleteState(@RequestParam UUID uuid) {
        try {
            equipmentStateService.deleteById(uuid);
            return ResponseEntity.status(201).body("State deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not delete state...");
        }
    }

    @PutMapping("/update-state/")
    public ResponseEntity deleteState(@RequestBody EquipmentStateRequest equipmentStateRequest) {
        try {
            equipmentStateService.update(equipmentStateRequest);
            return ResponseEntity.status(201).body("State updated!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not update state...");
        }
    }

}
