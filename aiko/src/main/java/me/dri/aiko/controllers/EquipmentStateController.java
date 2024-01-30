package me.dri.aiko.controllers;

import me.dri.aiko.entities.dto.EquipmentStateInputDTO;
import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;
import me.dri.aiko.services.interfaces.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipments/state")
public class EquipmentStateController {


    private final EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentStateController(EquipmentStateService equipmentStateService) {
        this.equipmentStateService = equipmentStateService;
    }

    @GetMapping
    public List<EquipmentStateResponseDTO> findAll() {
        return equipmentStateService.findALl();
    }

    @GetMapping(path = "/{nameEquipmentState}")
    public ResponseEntity<EquipmentStateResponseDTO> findByName(@PathVariable String nameEquipmentState) {
        return ResponseEntity.ok(this.equipmentStateService.findEquipmentStateByName(nameEquipmentState));
    }

    @GetMapping(path = "/id/{idEquipmentState}")
    public ResponseEntity<EquipmentStateResponseDTO> findById(@PathVariable String idEquipmentState) {
        return ResponseEntity.ok(this.equipmentStateService.findEquipmentStateById(idEquipmentState));
    }

    @PostMapping
    public ResponseEntity<EquipmentStateResponseDTO> createEquipmentState(@RequestBody EquipmentStateInputDTO newEquipmentState) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.equipmentStateService.createEquipmentState(newEquipmentState));
    }
}
