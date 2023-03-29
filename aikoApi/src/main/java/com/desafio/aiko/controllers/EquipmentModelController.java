package com.desafio.aiko.controllers;

import com.desafio.aiko.models.entities.EquipmentModel;
import com.desafio.aiko.models.request.EquipmentModelRequest;
import com.desafio.aiko.repositories.EquipmentModelRepository;
import com.desafio.aiko.services.EquipmentModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/equipmentModels")
@Slf4j
public class EquipmentModelController {
    @Autowired
    EquipmentModelService equipmentModelService;
    @Autowired
    EquipmentModelRepository equipmentModelRepository;

    @PostMapping("/create-equipmentModel")
    public ResponseEntity createEquipment(@RequestBody EquipmentModelRequest equipmentModelRequest) {
        try {
            equipmentModelService.create(equipmentModelRequest);
            return ResponseEntity.status(201).body("Equipment model created!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not create equipment model...");
        }


    }

    @DeleteMapping("/delete-equipmentModel")
    public ResponseEntity deleteEquipmentModelById(@RequestParam UUID id) {
        try {
            equipmentModelService.delete(id);
            return ResponseEntity.status(200).body("Equipment model deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not delete eqiupment model");
        }
    }
    @PutMapping("/update-equipment/")
    public ResponseEntity updateEquipmentModel(@RequestBody EquipmentModelRequest equipmentModelRequest){

        try {
            equipmentModelService.update(equipmentModelRequest);
            return ResponseEntity.status(200).body("Equipment model updated!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not update equipment model...");
        }
    }


    @GetMapping("/get-equipmentModel/")
    public ResponseEntity findAllEquipments() {
        try {
            List<EquipmentModelRequest> equipmentModelRequest = equipmentModelService.findAll();
            return ResponseEntity.status(200).body(equipmentModelRequest);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not get equipment models...");
        }
    }

    @GetMapping("/get-equipmentModels")
    public ResponseEntity findById(@RequestParam UUID uuid) {
        try {
            Optional<EquipmentModel> equipmentModel = equipmentModelService.findEquipmentModelById(uuid);
            return ResponseEntity.status(200).body(equipmentModel);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not return model");
        }
    }
}
