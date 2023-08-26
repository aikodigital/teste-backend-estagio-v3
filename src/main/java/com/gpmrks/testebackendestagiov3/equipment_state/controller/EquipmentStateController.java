package com.gpmrks.testebackendestagiov3.equipment_state.controller;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateForm;
import com.gpmrks.testebackendestagiov3.equipment_state.hateoas.EquipmentStateHateoas;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
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
@RequestMapping("/equipments-states")
@Tag(name = "Equipment State Controller")
public class EquipmentStateController {

    private EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentStateController(EquipmentStateService equipmentStateService) {
        this.equipmentStateService = equipmentStateService;
    }

    @GetMapping
    @Operation(summary = "Lista de Estados de Funcionamento de Equipamentos", description = "Lista todos os Estados de Funcionamento dos Equipamentos")
    public ResponseEntity<List<EquipmentStateDTO>> getAllEquipmentsStates() {
        List<EquipmentStateDTO> equipmentStatesDTO = equipmentStateService.getAllEquipmentsStates();
        EquipmentStateHateoas.toHateoasList(equipmentStatesDTO);
        return ResponseEntity.ok().body(equipmentStatesDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de Estado de Funcionamento de Equipamento", description = "Consulta um determinado Estado de Funcionamento dos Equipamentos")
    public ResponseEntity<EquipmentStateDTO> getEquipmentStateById(@PathVariable UUID id) {
        EquipmentStateDTO equipmentStateDTO = equipmentStateService.getEquipmentStateById(id);
        EquipmentStateHateoas.toHateoas(equipmentStateDTO.getId(), equipmentStateDTO);
        return ResponseEntity.ok().body(equipmentStateDTO);
    }

    @PostMapping
    @Operation(summary = "Criação de Estado de Funcionamento de Equipamentos", description = "Cria um novo Estado de Funcionamento dos Equipamentos")
    public ResponseEntity<EquipmentStateDTO> createEquipmentState(@RequestBody @Valid EquipmentStateForm equipmentStateToCreate, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentStateDTO equipmentStateDTO = equipmentStateService.createEquipmentState(equipmentStateToCreate);
        URI uri = uriComponentsBuilder.path("/equipments-states/{id}").buildAndExpand(equipmentStateDTO.getId()).toUri();
        EquipmentStateHateoas.toHateoas(equipmentStateDTO.getId(), equipmentStateDTO);
        return ResponseEntity.created(uri).body(equipmentStateDTO);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualização de Estado de Funcionamento de Equipamentos", description = "Atualiza um Estado de Funcionamento dos Equipamentos pelo ID do Estado de Funcionamento")
    public ResponseEntity<EquipmentStateDTO> updateEquipmentState(@PathVariable UUID id, @RequestBody EquipmentStateForm updatedEquipmentState) {
        EquipmentStateDTO equipmentStateDTO = equipmentStateService.updateEquipmentState(id, updatedEquipmentState);
        EquipmentStateHateoas.toHateoas(equipmentStateDTO.getId(), equipmentStateDTO);
        return ResponseEntity.ok().body(equipmentStateDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Exclusão de Estado de Funcionamento de Equipamentos", description = "Exclui um Estado de Funcionamento dos Equipamentos pelo ID do Estado de Funcionamento")
    public ResponseEntity<Void> deleteEquipmentState(@PathVariable UUID id) {
        equipmentStateService.deleteEquipmentState(id);
        return ResponseEntity.noContent().build();
    }
}
