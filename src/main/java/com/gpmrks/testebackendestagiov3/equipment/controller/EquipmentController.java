package com.gpmrks.testebackendestagiov3.equipment.controller;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.hateoas.EquipmentHateoas;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipments")
@Tag(name = "Equipment Controller")
public class EquipmentController {

    private EquipmentService equipmentService;

    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService, EquipmentModelService equipmentModelService) {
        this.equipmentService = equipmentService;
        this.equipmentModelService = equipmentModelService;
    }

    @GetMapping
    @Operation(summary = "Lista de Equipamentos", description = "Lista todos os Equipamentos")
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        List<EquipmentDTO> equipmentDTOS = equipments.stream()
                .map(Equipment::equipmentToDTO)
                .toList();
        EquipmentHateoas.toHateoasList(equipmentDTOS);
        return ResponseEntity.ok().body(equipmentDTOS);
    }

    @GetMapping("/model/{modelId}")
    @Operation(summary = "Lista de Equipamentos por Modelo", description = "Lista todos os Equipamentos de determinado Modelo, pelo ID do Modelo")
    public ResponseEntity<List<EquipmentDTO>> getEquipmentByModelId(@PathVariable UUID modelId) {
        List<Equipment> equipments = equipmentService.getAllEquipmentsByModelId(modelId);
        List<EquipmentDTO> equipmentDTOS = equipments.stream()
                .map(Equipment::equipmentToDTO)
                .toList();
        EquipmentHateoas.toHateoasList(equipmentDTOS);
        return ResponseEntity.ok().body(equipmentDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de Equipamento", description = "Consulta determinado Equipamento por seu ID")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable UUID id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        EquipmentDTO equipmentDTO = equipment.equipmentToDTO();
        EquipmentHateoas.toHateoas(id ,equipmentDTO);
        return ResponseEntity.ok().body(equipmentDTO);
    }

    @PostMapping
    @Operation(summary = "Criação de Equipamento", description = "Cria um novo Equipamento, sendo necessário o nome do equipamento e o ID do Modelo")
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentForm equipmentToSave, UriComponentsBuilder uriComponentsBuilder) {
        Equipment equipment = equipmentService.createEquipment(equipmentToSave);
        EquipmentDTO equipmentDTO = equipment.equipmentToDTO();
        URI uri = uriComponentsBuilder.path("/equipments/{id}").buildAndExpand(equipment.getId()).toUri();
        EquipmentHateoas.toHateoas(equipmentDTO.getId(), equipmentDTO);
        return ResponseEntity.created(uri).body(equipmentDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de Equipamento", description = "Atualiza um Equipamento por seu ID, podendo atualizar o nome do Equipamento e o Modelo")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable UUID id, @RequestBody EquipmentForm equipmentToUpdate) {
        Equipment equipment = equipmentService.updateEquipment(id, equipmentToUpdate);
        EquipmentDTO equipmentDTO = equipment.equipmentToDTO();
        EquipmentHateoas.toHateoas(equipmentDTO.getId(), equipmentDTO);
        return ResponseEntity.ok().body(equipmentDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Equipamento", description = "Exclui um Equipamento por seu ID")
    public ResponseEntity<Void> deleteEquipment(@PathVariable UUID id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
