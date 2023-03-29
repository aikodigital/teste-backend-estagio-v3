package com.api.aikobackendteste.controllers;
import com.api.aikobackendteste.models.EquipmentStateHistoryModel;
import com.api.aikobackendteste.services.EquipmentStateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-state-history")
public class EquipmentStateHistoryController {

    @Autowired
    EquipmentStateHistoryService equipmentStateHistoryService;

    @PostMapping
    public ResponseEntity<EquipmentStateHistoryModel> saveEquipmentStateHistory(@RequestBody EquipmentStateHistoryModel equipmentStateHistoryModel) {
        EquipmentStateHistoryModel savedEquipmentStateHistoryModel = equipmentStateHistoryService.save(equipmentStateHistoryModel);
        return new ResponseEntity<>(savedEquipmentStateHistoryModel, HttpStatus.CREATED);
    }

    @GetMapping("/{equipmentId}/{date}")
    public ResponseEntity<EquipmentStateHistoryModel> getEquipmentStateHistory(@PathVariable UUID equipmentId, @PathVariable Date date) {
        EquipmentStateHistoryModel equipmentStateHistoryModel = equipmentStateHistoryService.get(equipmentId, date);
        return new ResponseEntity<>(equipmentStateHistoryModel, HttpStatus.OK);
    }

    @DeleteMapping("/{equipmentId}/{date}")
    public ResponseEntity<String> deleteEquipmentStateHistory(@PathVariable UUID equipmentId, @PathVariable Date date) {
        String result = equipmentStateHistoryService.delete(equipmentId, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EquipmentStateHistoryModel> updateEquipmentStateHistory(@RequestBody EquipmentStateHistoryModel equipmentStateHistoryModel) {
        EquipmentStateHistoryModel updatedEquipmentStateHistoryModel = equipmentStateHistoryService.update(equipmentStateHistoryModel);
        return new ResponseEntity<>(updatedEquipmentStateHistoryModel, HttpStatus.OK);
    }
}