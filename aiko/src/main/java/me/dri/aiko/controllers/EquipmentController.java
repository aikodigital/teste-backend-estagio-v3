package me.dri.aiko.controllers;


import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentResponseDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;
import me.dri.aiko.services.EquipmentServiceImpl;
import me.dri.aiko.services.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final EquipmentService service;

    @Autowired
    public EquipmentController(EquipmentServiceImpl service) {
        this.service = service;
    }
    @GetMapping
    public List<EquipmentResponseDTO> findAll() {
        return this.service.findAll();
    }
    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody EquipmentInputDTO equipmentInputDTO) {
        return  ResponseEntity.ok(this.service.createEquipment(equipmentInputDTO));
    }

    @DeleteMapping(path = "/{nameEquipment}")
    public ResponseEntity deleteByName(@PathVariable String nameEquipment) {
        this.service.deleteEquipmentByName(nameEquipment);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/{nameEquipment}")
    public ResponseEntity updateEquipmentByName(@PathVariable String nameEquipment, @RequestBody EquipmentUpdateDTO equipmentUpdateDTO) {
        return ResponseEntity.ok(this.service.updateEquipmentByName(nameEquipment, equipmentUpdateDTO));
    }

    @PutMapping(path = "/id/{idEquipment}")
    public ResponseEntity updateEquipmentById(@PathVariable String idEquipment , @RequestBody EquipmentUpdateDTO equipmentUpdateDTO) {
        return ResponseEntity.ok(this.service.updateEquipmentById(idEquipment, equipmentUpdateDTO));
    }
}
