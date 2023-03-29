package com.api.aikobackendteste.controllers;

import com.api.aikobackendteste.models.EquipmentModelModel;
import com.api.aikobackendteste.services.EquipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-models")
public class EquipmentModelController {

    @Autowired
    private EquipmentModelService equipmentModelService;

    @PostMapping
    public ResponseEntity<EquipmentModelModel> create(@RequestBody EquipmentModelModel equipmentModelModel) {
        EquipmentModelModel createdEquipmentModel = equipmentModelService.save(equipmentModelModel);
        return new ResponseEntity<>(createdEquipmentModel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentModelModel>> getAll() {
        List<EquipmentModelModel> equipmentModels = equipmentModelService.getAll();
        return new ResponseEntity<>(equipmentModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentModelModel> getById(@PathVariable UUID id) {
        EquipmentModelModel equipmentModel = equipmentModelService.get(id);
        return new ResponseEntity<>(equipmentModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String result = equipmentModelService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentModelModel> update(@PathVariable UUID id, @RequestBody EquipmentModelModel equipmentModelModel) {
        EquipmentModelModel updatedEquipmentModel = equipmentModelService.update(equipmentModelModel, id);
        return new ResponseEntity<>(updatedEquipmentModel, HttpStatus.OK);
    }

}