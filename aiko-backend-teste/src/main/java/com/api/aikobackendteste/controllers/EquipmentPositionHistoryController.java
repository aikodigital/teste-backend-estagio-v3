package com.api.aikobackendteste.controllers;

import com.api.aikobackendteste.models.EquipmentPositionHistoryModel;
import com.api.aikobackendteste.services.EquipmentPositionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-Position-History")
public class EquipmentPositionHistoryController {

    @Autowired
    EquipmentPositionHistoryService equipmentPositionHistoryService;

    @PostMapping("/")
    public ResponseEntity<EquipmentPositionHistoryModel> save(@RequestBody EquipmentPositionHistoryModel equipmentPositionHistoryModel) {
        return new ResponseEntity<>(equipmentPositionHistoryService.save(equipmentPositionHistoryModel), HttpStatus.OK);
    }

    @GetMapping("/{equipmentId}/{date}")
    public ResponseEntity<EquipmentPositionHistoryModel> get(@PathVariable UUID equipmentId, @PathVariable Date date) {
        return new ResponseEntity<>(equipmentPositionHistoryService.get(date, equipmentId), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<EquipmentPositionHistoryModel> update(@RequestBody EquipmentPositionHistoryModel equipmentPositionHistoryModel) {
        return new ResponseEntity<>(equipmentPositionHistoryService.update(equipmentPositionHistoryModel), HttpStatus.OK);
    }

    @DeleteMapping("/{equipmentId}/{date}")
    public ResponseEntity<String> delete(@PathVariable UUID equipmentId, @PathVariable Date date) {
        return new ResponseEntity<>(equipmentPositionHistoryService.delete(equipmentId, date), HttpStatus.OK);
    }
}