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
@RequestMapping("/actual-position")
public class ActualPositionController {

    @Autowired
    EquipmentPositionHistoryService equipmentPositionHistoryService;
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentPositionHistoryModel> getPosition(@PathVariable UUID id, @RequestBody Date date) {
        EquipmentPositionHistoryModel equipmentPositionHistory=equipmentPositionHistoryService.get(date,id);
        return new ResponseEntity<>(equipmentPositionHistory, HttpStatus.OK);
    }
}
