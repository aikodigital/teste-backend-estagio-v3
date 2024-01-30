package me.dri.aiko.controllers;

import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;
import me.dri.aiko.services.interfaces.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
