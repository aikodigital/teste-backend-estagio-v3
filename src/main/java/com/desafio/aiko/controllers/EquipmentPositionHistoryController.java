package com.desafio.aiko.controllers;

import com.desafio.aiko.models.id.EquipmentPositionHistoryId;
import com.desafio.aiko.models.request.EquipmentPositionHistoryRequest;
import com.desafio.aiko.repositories.EquipmentPositionHistoryRepository;
import com.desafio.aiko.services.EquipmentPositionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-position-history")
public class EquipmentPositionHistoryController {
    @Autowired
    EquipmentPositionHistoryService equipmentPositionHistoryService;
    @Autowired
    EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;

    @PostMapping("/create-position-history/")
    public ResponseEntity createEquipment(@RequestBody EquipmentPositionHistoryRequest equipmentPositionHistoryRequest) {
        try {
            equipmentPositionHistoryService.create(equipmentPositionHistoryRequest);
            return ResponseEntity.status(201).body("Position history created!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not create position history...");
        }
    }
    @GetMapping("/get-realtime/")
    public ResponseEntity findLastPositions() {
        try {
            List<EquipmentPositionHistoryRequest> equipmentPositionHistoryRequests = equipmentPositionHistoryService.findLastPositions();
            return ResponseEntity.status(200).body(equipmentPositionHistoryRequests);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not get positions...");
        }
    }

    @PutMapping("/update-position/")
    public ResponseEntity updateEarning(@RequestBody EquipmentPositionHistoryRequest equipmentPositionHistoryRequest ) {
        try {
            equipmentPositionHistoryService.update(equipmentPositionHistoryRequest);
            return ResponseEntity.status(200).body("Position updated!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not get positions...");
        }
    }
    @DeleteMapping("/delete-position")
    public ResponseEntity deleteEarning(@RequestParam UUID uuid) {
        try {
            equipmentPositionHistoryService.delete(uuid);
            return ResponseEntity.status(200).body("Position deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not delete positions...");
        }
    }

}
