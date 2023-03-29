package com.desafio.aiko.controllers;


import com.desafio.aiko.models.request.EquipmentModelStateHourlyEarningsRequest;
import com.desafio.aiko.repositories.EquipmentModelStateHourlyEarningsRepository;
import com.desafio.aiko.services.EquipmentModelStateHourlyEarningsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment-model-earnings")
@Slf4j
public class EquipmentModelStateHourlyEarningsController {
    @Autowired
    EquipmentModelStateHourlyEarningsService equipmentModelStateHourlyEarningsService;

    @GetMapping("/get-all-earnings/")
    public ResponseEntity getAllEarnings() {
        try {
            List<EquipmentModelStateHourlyEarningsRequest> equipmentModelStateHourlyEarningsList = equipmentModelStateHourlyEarningsService.equipmentModelStateHourlyEarningsList();
            return ResponseEntity.status(201).body(equipmentModelStateHourlyEarningsList);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not find any earning here!");
        }
    }

    @PostMapping("/create-earning/")
    public ResponseEntity createEarning(@RequestBody EquipmentModelStateHourlyEarningsRequest equipmentModelStateHourlyEarnings) {
        try {
            equipmentModelStateHourlyEarningsService.create(equipmentModelStateHourlyEarnings);
            return ResponseEntity.status(201).body("earning created");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not create earning...");
        }
    }

    @DeleteMapping("/delete-earning/")
    public ResponseEntity deleteEarning(@RequestBody EquipmentModelStateHourlyEarningsRequest equipmentModelStateHourlyEarningsRequest) {
        try {
            equipmentModelStateHourlyEarningsService.delete(equipmentModelStateHourlyEarningsRequest);
            return ResponseEntity.status(201).body("earning deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not delete earning...");
        }
    }
    @PutMapping("/update-earning/")
    public ResponseEntity updateEarning(@RequestBody EquipmentModelStateHourlyEarningsRequest equipmentModelStateHourlyEarningsRequest){
        try {
            equipmentModelStateHourlyEarningsService.update(equipmentModelStateHourlyEarningsRequest);
            return ResponseEntity.status(201).body("earning updated!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Could not update earning...");
        }
    }

}
