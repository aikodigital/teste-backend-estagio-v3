package com.api.aikobackendteste.controllers;
import com.api.aikobackendteste.models.EquipmentModelStateHourlyEarningModel;
import com.api.aikobackendteste.services.EquipmentModelStateHourlyEarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment-model-state-hourly-earning")
public class EquipmentModelStateHourlyEarningController {

    @Autowired
    private EquipmentModelStateHourlyEarningService equipmentModelStateHourlyEarningService;

    @PostMapping("/")
    public ResponseEntity<EquipmentModelStateHourlyEarningModel> save(@RequestBody EquipmentModelStateHourlyEarningModel equipmentModelStateHourlyEarningModel) {
        EquipmentModelStateHourlyEarningModel savedEquipmentModelStateHourlyEarningModel = equipmentModelStateHourlyEarningService.save(equipmentModelStateHourlyEarningModel);
        return new ResponseEntity<>(savedEquipmentModelStateHourlyEarningModel, HttpStatus.CREATED);
    }

    @GetMapping("/{modelId}/{stateID}")
    public ResponseEntity<EquipmentModelStateHourlyEarningModel> get(@PathVariable UUID modelId, @PathVariable UUID stateID) {
        EquipmentModelStateHourlyEarningModel equipmentModelStateHourlyEarningModel = equipmentModelStateHourlyEarningService.get(modelId, stateID);
        return new ResponseEntity<>(equipmentModelStateHourlyEarningModel, HttpStatus.OK);
    }

    @DeleteMapping("/{modelId}/{stateId}")
    public ResponseEntity<String> delete(@PathVariable UUID modelId, @PathVariable UUID stateId) {
        String message = equipmentModelStateHourlyEarningService.delete(modelId, stateId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<EquipmentModelStateHourlyEarningModel> update(@RequestBody EquipmentModelStateHourlyEarningModel equipmentModelStateHourlyEarningModel) {
        EquipmentModelStateHourlyEarningModel updatedEquipmentModelStateHourlyEarningModel = equipmentModelStateHourlyEarningService.update(equipmentModelStateHourlyEarningModel);
        return new ResponseEntity<>(updatedEquipmentModelStateHourlyEarningModel, HttpStatus.OK);
    }
}