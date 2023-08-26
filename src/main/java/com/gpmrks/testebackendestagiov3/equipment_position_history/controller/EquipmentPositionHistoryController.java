package com.gpmrks.testebackendestagiov3.equipment_position_history.controller;

import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionForm;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_position_history.hateoas.EquipmentPositionHistoryHateoas;
import com.gpmrks.testebackendestagiov3.equipment_position_history.service.EquipmentPositionHistoryService;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
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
@RequestMapping("equipments-positions-histories")
@Tag(name = "Equipment Position History Controller")
public class EquipmentPositionHistoryController {

    private EquipmentPositionHistoryService equipmentPositionHistoryService;

    @Autowired
    public EquipmentPositionHistoryController(EquipmentPositionHistoryService equipmentPositionHistoryService) {
        this.equipmentPositionHistoryService = equipmentPositionHistoryService;
    }

    @GetMapping
    @Operation(summary = "Lista o Histórico de Posições de Equipamentos", description = "Lista todos os históricos de Posições de Equipamentos")
    public ResponseEntity<List<EquipmentPositionHistoryDTO>> getAllEquipmentsPositionsHistories() {
        List<EquipmentPositionHistoryDTO> equipmentPositionHistoriesDTO = equipmentPositionHistoryService.getAllEquipmentsPositionHistories();
        EquipmentPositionHistoryHateoas.toHateoasList(equipmentPositionHistoriesDTO);
        return ResponseEntity.ok().body(equipmentPositionHistoriesDTO);
    }

    @GetMapping("/{equipmentId}")
    @Operation(summary = "Lista de Histórico de Posições de um determinado Equipamento", description = "Lista o histórico de Posições de um determinado Equipamento por seu ID")
    public ResponseEntity<List<EquipmentPositionHistoryDTO>> getEquipmentPositionHistoryByEquipmentId(@PathVariable UUID equipmentId) {
        List<EquipmentPositionHistoryDTO> equipmentPositionHistoriesDTO = equipmentPositionHistoryService.getEquipmentPositionHistoryByEquipId(equipmentId);
        EquipmentPositionHistoryHateoas.toHateoasList(equipmentPositionHistoriesDTO);
        return ResponseEntity.ok().body(equipmentPositionHistoriesDTO);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Lista de Histórico de Posições de Equipamentos de um determinado Período", description = "Lista o histórico de Posições de Equipamentos em um determinado período, sendo necessário passar data inicial e data final, padrão dd/MM/yyyy HH:mm:ss")
    public ResponseEntity<List<EquipmentPositionHistoryDTO>> getEquipmentPositionHistoryByDateRange(@RequestBody EquipmentHistoryDateForm equipmentHistoryDateForm) {
        List<EquipmentPositionHistoryDTO> equipmentPositionHistoriesDTO = equipmentPositionHistoryService.getEquipmentPositionHistoryByDate(equipmentHistoryDateForm);
        EquipmentPositionHistoryHateoas.toHateoasList(equipmentPositionHistoriesDTO);
        return ResponseEntity.ok().body(equipmentPositionHistoriesDTO);
    }

    @PostMapping("/{equipmentId}")
    @Operation(summary = "Registrar a Posição de determinado Equipamento no Histórico", description = "Registra o posicionamento de determinado Equipamento por seu ID")
    public ResponseEntity<EquipmentPositionHistoryDTO> registerEquipmentPosition(@PathVariable UUID equipmentId, @RequestBody @Valid EquipmentPositionForm equipmentPositionToCreate, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentPositionHistoryDTO equipmentPositionHistoryDTO = equipmentPositionHistoryService.registerEquipmentPosition(equipmentId, equipmentPositionToCreate);
        URI uri = uriComponentsBuilder.path("/equipments-positions-histories/{equipmentId}").buildAndExpand(equipmentId).toUri();
        EquipmentPositionHistoryHateoas.toHateoas(equipmentId, equipmentPositionHistoryDTO);
        return ResponseEntity.created(uri).body(equipmentPositionHistoryDTO);
    }

    @DeleteMapping("/{equipmentId}")
    @Operation(summary = "Excluir Histórico de Posição de Equipamento", description = "Exclui o Histórico de posicionamento de determinado Equipamento por seu ID")
    public ResponseEntity<Void> deleteEquipmentPositionHistory(@PathVariable UUID equipmentId) {
        equipmentPositionHistoryService.deleteEquipmentPositionHistory(equipmentId);
        return ResponseEntity.noContent().build();
    }
}
