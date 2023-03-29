package com.desafio.aiko.controllers;

import com.desafio.aiko.models.request.EquipmentStateHistoryRequest;
import com.desafio.aiko.services.EquipmentStateHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-state-history")
@Slf4j
public class EquipmentStateHistoryController {
    @Autowired
    EquipmentStateHistoryService equipmentStateHistoryService;

    @PostMapping("/create-state-history/")
    public ResponseEntity createStateHistory(@RequestBody EquipmentStateHistoryRequest equipmentStateHistoryRequest) {
        try {
            equipmentStateHistoryService.create(equipmentStateHistoryRequest.getEquipmentId(), equipmentStateHistoryRequest.getEquipmentStateId());
            return ResponseEntity.status(201).body("Equipment history created!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not create equipment history...");
        }


    }

    @GetMapping("/get-state-history/")
    public ResponseEntity findAllEquipments() {
        try {
            List<EquipmentStateHistoryRequest> equipmentStateHistory = equipmentStateHistoryService.findLastState();
            return ResponseEntity.status(200).body(equipmentStateHistory);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not get equipments...");
        }
    }

    @PutMapping("/update-state-history/")
    public ResponseEntity updateStateHistory(@RequestBody EquipmentStateHistoryRequest equipmentStateHistoryRequest) {
        try {
            equipmentStateHistoryService.update(equipmentStateHistoryRequest);
            return ResponseEntity.status(200).body("State history updated!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not update state history...");
        }
    }

    @DeleteMapping("/delete-state-history")
    public ResponseEntity deleteEquipmentStateHistoryById(@RequestParam UUID uuid) {
        try {
            equipmentStateHistoryService.delete(uuid);
            return ResponseEntity.status(200).body("State history deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed");
        }
    }


}
