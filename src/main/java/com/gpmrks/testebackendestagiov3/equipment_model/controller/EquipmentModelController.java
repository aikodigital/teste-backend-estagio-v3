package com.gpmrks.testebackendestagiov3.equipment_model.controller;

import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelDTO;
import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelForm;
import com.gpmrks.testebackendestagiov3.equipment_model.hateoas.EquipmentModelHateoas;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("equipments-models")
@Tag(name = "Equipment Model Controller")
public class EquipmentModelController {

    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentModelController(EquipmentModelService equipmentModelService) {
        this.equipmentModelService = equipmentModelService;
    }

    @GetMapping
    @Operation(summary = "Lista de Modelos de Equipamentos", description = "Lista todos os Modelos de Equipamentos")
    public ResponseEntity<List<EquipmentModelDTO>> getAllEquipmentsModels() {
        List<EquipmentModelDTO> equipmentModelDTOS = equipmentModelService.getAllEquipmentsModels();
        EquipmentModelHateoas.toHateoasList(equipmentModelDTOS);
        return ResponseEntity.ok().body(equipmentModelDTOS);
    }

    @GetMapping("{id}")
    @Operation(summary = "Consulta de Modelo", description = "Consulta determinado Modelo por seu ID")
    public ResponseEntity<EquipmentModelDTO> getEquipmentModelById(@PathVariable UUID id) {
        EquipmentModelDTO equipmentModelDTO = equipmentModelService.getEquipModelById(id);
        EquipmentModelHateoas.toHateoas(equipmentModelDTO.getId(), equipmentModelDTO);
        return ResponseEntity.ok().body(equipmentModelDTO);
    }

    @PostMapping
    @Operation(summary = "Criação de Modelo de Equipamento", description = "Cria um novo Modelo de Equipamento sendo necessário passar um Nome de Modelo")
    public ResponseEntity<EquipmentModelDTO> createEquipmentModel(@RequestBody @Valid EquipmentModelForm equipmentModelToSave, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentModelDTO equipmentModelDTO = equipmentModelService.createEquipmentModel(equipmentModelToSave);
        URI uri = uriComponentsBuilder.path("/equipments-models/{id}").buildAndExpand(equipmentModelDTO.getId()).toUri();
        EquipmentModelHateoas.toHateoas(equipmentModelDTO.getId(), equipmentModelDTO);
        return ResponseEntity.created(uri).body(equipmentModelDTO);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza Modelo de Equipamento", description = "Atualiza determinado Modelo de Equipamento pelo ID do Modelo")
    public ResponseEntity<EquipmentModelDTO> updateEquipment(@PathVariable UUID id, @RequestBody @Valid EquipmentModelForm updatedEquipmentModel) {
        EquipmentModelDTO equipmentModelDTO = equipmentModelService.updateEquipmentModel(id, updatedEquipmentModel);
        EquipmentModelHateoas.toHateoas(equipmentModelDTO.getId(), equipmentModelDTO);
        return ResponseEntity.ok().body(equipmentModelDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Exclusão de Modelo de Equipamento", description = "Exclui um Modelo de Equipamento por seu ID")
    public ResponseEntity<Void> deleteEquipmentModel(@PathVariable UUID id) {
        equipmentModelService.deleteEquipmentModel(id);
        return ResponseEntity.noContent().build();
    }
}
