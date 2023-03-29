package com.api.aikobackendteste.controllers;

import com.api.aikobackendteste.models.EquipmentStateModel;
import com.api.aikobackendteste.services.EquipmentStateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/equipment-states")
public class EquipmentStateController {

    @Autowired
    private EquipmentStateService equipmentStateService;

    @PostMapping("")
    public ResponseEntity<EquipmentStateModel> save(@RequestBody @Valid EquipmentStateModel equipmentStateModel) {
        EquipmentStateModel savedEquipmentStateModel = equipmentStateService.save(equipmentStateModel);
        return new ResponseEntity<>(savedEquipmentStateModel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentStateModel> get(@PathVariable UUID id) {
        EquipmentStateModel equipmentStateModel = equipmentStateService.get(id);
        return new ResponseEntity<>(equipmentStateModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String result = equipmentStateService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentStateModel> update(@PathVariable UUID id, @RequestBody @Valid EquipmentStateModel equipmentStateModel) {
        equipmentStateModel.setId(id);
        EquipmentStateModel updatedEquipmentStateModel = equipmentStateService.update(equipmentStateModel);
        return new ResponseEntity<>(updatedEquipmentStateModel, HttpStatus.OK);
    }
}