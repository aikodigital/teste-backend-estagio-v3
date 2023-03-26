package com.gpmrks.testebackendestagiov3.equipment.controller;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    private EquipmentService equipmentService;

    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService, EquipmentModelService equipmentModelService) {
        this.equipmentService = equipmentService;
        this.equipmentModelService = equipmentModelService;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        List<EquipmentDTO> equipmentDTOS = equipments.stream()
                .map(Equipment::equipmentToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentDTOS);
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<EquipmentDTO>> getEquipmentByModelId(@PathVariable UUID modelId) {
        List<Equipment> equipments = equipmentService.getAllEquipmentsByModelId(modelId);
        List<EquipmentDTO> equipmentDTOS = equipments.stream()
                .map(Equipment::equipmentToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable UUID id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        EquipmentDTO equipmentDTO = equipment.equipmentToDTO();
        return ResponseEntity.ok().body(equipmentDTO);
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentForm equipmentToSave, UriComponentsBuilder uriComponentsBuilder) {
        Equipment equipment = equipmentService.createEquipment(equipmentToSave);
        EquipmentDTO equipmentDTO = equipment.equipmentToDTO();
        URI uri = uriComponentsBuilder.path("/equipments/{id}").buildAndExpand(equipment.getId()).toUri();
        return ResponseEntity.created(uri).body(equipmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable UUID id, @RequestBody EquipmentForm equipmentToUpdate){
        Equipment equipment = equipmentService.updateEquipment(id, equipmentToUpdate);
        EquipmentDTO equipmentDTO = equipment.equipmentToDTO();
        return ResponseEntity.ok().body(equipmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable UUID id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
