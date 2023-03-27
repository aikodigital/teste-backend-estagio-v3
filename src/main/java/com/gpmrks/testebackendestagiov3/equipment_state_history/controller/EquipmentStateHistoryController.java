package com.gpmrks.testebackendestagiov3.equipment_state_history.controller;

import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_state_history.hateoas.EquipmentStateHistoryHateoas;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.service.EquipmentStateHistoryService;
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
@RequestMapping("equipments-states-histories")
@Tag(name = "Equipment State History Controller")
public class EquipmentStateHistoryController {

    private EquipmentStateHistoryService equipmentStateHistoryService;

    @Autowired
    public EquipmentStateHistoryController(EquipmentStateHistoryService equipmentStateHistoryService) {
        this.equipmentStateHistoryService = equipmentStateHistoryService;
    }

    @GetMapping
    @Operation(summary = "Listar o Histórico de Estados de Funcionamento dos Equipamentos", description = "Lista o Histórico de Estados de Funcionamento dos Equipamentos")
    public ResponseEntity<List<EquipmentStateHistoryDTO>> getAllEquipmentsStateHistories() {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.getAllEquipmentsStateHistories();
        List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS = equipmentStateHistories.stream()
                .map(EquipmentStateHistory::equipmentStateHistoryDTO)
                .toList();
        EquipmentStateHistoryHateoas.toHateoasList(equipmentStateHistoryDTOS);
        return ResponseEntity.ok().body(equipmentStateHistoryDTOS);
    }

    @GetMapping("/equipment/{equipmentId}")
    @Operation(summary = "Listar o Histórico de Estados de Funcionamento de um Equipamento específico", description = "Lista o Histórico de Estados de Funcionamento de um determinado Equipamento por seu ID")
    public ResponseEntity<List<EquipmentStateHistoryDTO>> getEquipmentStateHistoryByEquipmentId(@PathVariable UUID equipmentId) {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.getEquipmentStateHistoryByEquipId(equipmentId);
        List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS = equipmentStateHistories.stream()
                .map(EquipmentStateHistory::equipmentStateHistoryDTO)
                .toList();
        EquipmentStateHistoryHateoas.toHateoasList(equipmentStateHistoryDTOS);
        return ResponseEntity.ok().body(equipmentStateHistoryDTOS);
    }

    @GetMapping("/state/{stateId}")
    @Operation(summary = "Listar o Histórico de um determinado Estado de Funcionamento dos Equipamentos", description = "Lista o Histórico de um determinado Estado de Funcionamento dos Equipamentos pelo ID do Estado de Funcionamento")
    public ResponseEntity<List<EquipmentStateHistoryDTO>> getEquipmentStateHistoryByStateId(@PathVariable UUID stateId) {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.getEquipmentStateHistoryByStateId(stateId);
        List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS = equipmentStateHistories.stream()
                .map(EquipmentStateHistory::equipmentStateHistoryDTO)
                .toList();
        EquipmentStateHistoryHateoas.toHateoasList(equipmentStateHistoryDTOS);
        return ResponseEntity.ok().body(equipmentStateHistoryDTOS);
    }

    @GetMapping("/{equipmentId}/{stateId}")
    @Operation(summary = "Listar o Histórico de Estados de Funcionamento de um determinado Equipamento e determinado Estado de Funcionamento", description = "Lista o Histórico de Estados de Funcionamento de um determinado Equipamento e Estado de Funcionamento pelo ID do Equipamento e ID de Estado")
    public ResponseEntity<List<EquipmentStateHistoryDTO>> getEquipmentStateHistoryByEquipmentAndStateIds(@PathVariable UUID equipmentId, @PathVariable UUID stateId) {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.getEquipmentStateHistoryByEquipAndStateIds(equipmentId, stateId);
        List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS = equipmentStateHistories.stream()
                .map(EquipmentStateHistory::equipmentStateHistoryDTO)
                .toList();
        EquipmentStateHistoryHateoas.toHateoasList(equipmentStateHistoryDTOS);
        return ResponseEntity.ok().body(equipmentStateHistoryDTOS);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Listar o Histórico de Estados de Funcionamento dos Equipamentos de um determinado Período", description = "Lista o Histórico de Estados de Funcionamento dos Equipamentos de um determinado período, sendo necessário passar data inicial e data final, padrão dd/MM/yyyy HH:mm:ss")
    public ResponseEntity<List<EquipmentStateHistoryDTO>> getEquipmentStateHistoryByDate(@RequestBody EquipmentHistoryDateForm rangeDate) {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.getEquipmentStateHistoryByDate(rangeDate);
        List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS = equipmentStateHistories.stream()
                .map(EquipmentStateHistory::equipmentStateHistoryDTO)
                .toList();
        EquipmentStateHistoryHateoas.toHateoasList(equipmentStateHistoryDTOS);
        return ResponseEntity.ok().body(equipmentStateHistoryDTOS);
    }

    @PostMapping("/{equipmentId}/{stateId}")
    @Operation(summary = "Registrar um Estado de Funcionamento de determinado Equipamento no Histórico", description = "Registra o Estado de Funcionamento atual do Equipamento no Histórico")
    public ResponseEntity<EquipmentStateHistoryDTO> registerEquipmentStateHistory(@PathVariable UUID equipmentId, @PathVariable UUID stateId, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentStateHistory equipmentStateHistory = equipmentStateHistoryService.registerEquipmentState(equipmentId, stateId);
        EquipmentStateHistoryDTO equipmentStateHistoryDTO = equipmentStateHistory.equipmentStateHistoryDTO();
        URI uri = uriComponentsBuilder.path("/equipments-states-histories/{equipmentId}/{stateId}").buildAndExpand(equipmentStateHistory.getEquipment().getId(), equipmentStateHistory.getEquipmentState().getId()).toUri();
        EquipmentStateHistoryHateoas.toHateoas(equipmentId, stateId, equipmentStateHistoryDTO);
        return ResponseEntity.created(uri).body(equipmentStateHistoryDTO);
    }

    @DeleteMapping("/{equipmentId}")
    @Operation(summary = "Exclusão do Histórico de Estados de Funcionamento de um determinado Equipamento", description = "Realiza a exclusão do Histórico de Estados de Funcionamento de determinado Equipamento por seu ID")
    public ResponseEntity<Void> deleteEquipmentStateHistory(@PathVariable UUID equipmentId) {
        equipmentStateHistoryService.deleteEquipmentStateHistory(equipmentId);
        return ResponseEntity.noContent().build();
    }

}
