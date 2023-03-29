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
@RequestMapping("/actual-state")
public class ActualStateController {
    @Autowired
    EquipmentStateHistoryService equipmentStateHistoryService;
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentStateHistoryModel> getState(@PathVariable UUID id, @RequestBody Date date) {
        EquipmentStateHistoryModel equipmentStateHistory=equipmentStateHistoryService.get(id,date);
        return new ResponseEntity<>(equipmentStateHistory, HttpStatus.OK);
    }
}
