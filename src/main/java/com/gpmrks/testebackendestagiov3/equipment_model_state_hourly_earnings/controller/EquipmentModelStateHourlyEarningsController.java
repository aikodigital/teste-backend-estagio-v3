package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.controller;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.hateoas.EquipmentModelStateHourlyEarningsHateoas;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service.EquipmentModelStateHourlyEarningsService;
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
@RequestMapping("equipments-models-states-hourly-earnings")
@Tag(name = "Equipment Model State Hourly Earnings Controller")
public class EquipmentModelStateHourlyEarningsController {

    private EquipmentModelStateHourlyEarningsService equipmentModelStateHourlyEarningsService;

    @Autowired
    public EquipmentModelStateHourlyEarningsController(EquipmentModelStateHourlyEarningsService equipmentModelStateHourlyEarningsService) {
        this.equipmentModelStateHourlyEarningsService = equipmentModelStateHourlyEarningsService;
    }

    @GetMapping
    @Operation(summary = "Lista de Ganhos por Hora de Equipamentos por Modelos em Estados de Funcionamento", description = "Lista todos os ganhos por hora dos Equipamentos por seus modelos e estados de funcionamento")
    public ResponseEntity<List<EquipmentModelStateHourlyEarningsDTO>> getAllEquipmentsModelsStatesHourlyEarnings() {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList = equipmentModelStateHourlyEarningsService.getAllEquipmentsModelStateHourlyEarnings();
        List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsDTOS = equipmentModelStateHourlyEarningsList.stream()
                .map(EquipmentModelStateHourlyEarnings::equipmentModelStateHourlyEarningsToDTO)
                .toList();
        EquipmentModelStateHourlyEarningsHateoas.toHateoasList(equipmentModelStateHourlyEarningsDTOS);
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsDTOS);
    }

    @GetMapping("/model/{modelId}")
    @Operation(summary = "Lista de Ganhos por Hora de Equipamentos por um Modelo Específico em seus Estados de Funcionamento", description = "Lista todos os ganhos por hora dos Equipamentos de um Modelo específico em seus estados de funcionamento, pelo ID do Modelo")
    public ResponseEntity<List<EquipmentModelStateHourlyEarningsDTO>> getEquipmentsModelsStatesHourlyEarningsByModelId(@PathVariable UUID modelId) {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsByModelId = equipmentModelStateHourlyEarningsService.getEquipmentModelStateHourlyEarningsByModelId(modelId);
        List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsByModelIdDTOS = equipmentModelStateHourlyEarningsByModelId.stream()
                .map(EquipmentModelStateHourlyEarnings::equipmentModelStateHourlyEarningsToDTO)
                .toList();
        EquipmentModelStateHourlyEarningsHateoas.toHateoasList(equipmentModelStateHourlyEarningsByModelIdDTOS);
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsByModelIdDTOS);
    }

    @GetMapping("/state/{stateId}")
    @Operation(summary = "Lista de Ganhos por Hora de Equipamentos por um Estado de Funcionamento específico", description = "Lista todos os ganhos por hora dos Equipamentos em um Estado de funcionamento específico, pelo ID do Estado de Funcionamento")
    public ResponseEntity<List<EquipmentModelStateHourlyEarningsDTO>> getEquipmentsModelsStatesHourlyEarningsByStateId(@PathVariable UUID stateId) {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsByStateId = equipmentModelStateHourlyEarningsService.getEquipmentModelStateHourlyEarningsByStateId(stateId);
        List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsByStateIdDTOS = equipmentModelStateHourlyEarningsByStateId.stream()
                .map(EquipmentModelStateHourlyEarnings::equipmentModelStateHourlyEarningsToDTO)
                .toList();
        EquipmentModelStateHourlyEarningsHateoas.toHateoasList(equipmentModelStateHourlyEarningsByStateIdDTOS);
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsByStateIdDTOS);
    }

    @GetMapping("/{modelId}/{stateId}")
    @Operation(summary = "Consulta de Ganho por Hora de um Equipamento por especificidade de Modelo e Estado de Funcionamento", description = "Consulta o ganho por hora de um Equipamento por especificidade de Modelo e Estado de funcionamento, pelo ID de Modelo e ID de Estado de funcionamento")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> getEquipmentsModelsStatesHourlyEarningsByModelAndStateIds(@PathVariable UUID modelId, @PathVariable UUID stateId) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsService.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsByStateIdDTO = equipmentModelStateHourlyEarnings.equipmentModelStateHourlyEarningsToDTO();
        EquipmentModelStateHourlyEarningsHateoas.toHateoas(modelId, stateId, equipmentModelStateHourlyEarningsByStateIdDTO);
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsByStateIdDTO);
    }

    @PostMapping("{modelId}/{stateId}")
    @Operation(summary = "Criação de Ganho por Hora de Equipamento por Modelo e Estado de Funcionamento", description = "Cria um novo ganho por hora do Equipamento por seu modelo e estado de funcionamento, por ID do Modelo e ID do Estado de Funcionamento")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> createEquipmentModelStateHourlyEarnings(@PathVariable UUID modelId, @PathVariable UUID stateId, @RequestBody EquipmentModelStateHourlyEarningsForm value, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsService.createEquipmentModelStateHourlyEarnings(modelId, stateId, value);
        EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsDTO = equipmentModelStateHourlyEarnings.equipmentModelStateHourlyEarningsToDTO();
        URI uri = uriComponentsBuilder.path("equipments-models-states-hourly-earnings/{modelId}/{stateId}").buildAndExpand(equipmentModelStateHourlyEarnings.getEquipmentModel().getId(), equipmentModelStateHourlyEarnings.getEquipmentState().getId()).toUri();
        EquipmentModelStateHourlyEarningsHateoas.toHateoas(modelId, stateId, equipmentModelStateHourlyEarningsDTO);
        return ResponseEntity.created(uri).body(equipmentModelStateHourlyEarningsDTO);
    }

    @PutMapping("{modelId}/{stateId}")
    @Operation(summary = "Atualização do Ganho por Hora de Equipamento por Modelo em Estado de Funcionamento", description = "Atualiza o valor do ganho por hora do Equipamento por seu modelo e estado de funcionamento, pelo ID do Modelo e ID do Estado de Funcionamento")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> updateEquipmentModelStateHourlyEarnings(@PathVariable UUID modelId, @PathVariable UUID stateId, @RequestBody EquipmentModelStateHourlyEarningsForm value) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsService.updateEquipmentModelStateHourlyEarnings(modelId, stateId, value);
        EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsDTO = equipmentModelStateHourlyEarnings.equipmentModelStateHourlyEarningsToDTO();
        EquipmentModelStateHourlyEarningsHateoas.toHateoas(modelId, stateId, equipmentModelStateHourlyEarningsDTO);
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsDTO);
    }

    @DeleteMapping("{modelId}/{stateId}")
    @Operation(summary = "Exclusão do Ganho por Hora de Equipamento por Modelo em Estado de Funcionamento", description = "Realiza a exclusão do ganho por hora dos Equipamento por seu modelo e estado de funcionamento, pelo ID do Modelo e ID do Estado de Funcionamento")
    public ResponseEntity<Void> deleteEquipmentModelStateHourlyEarnings(@PathVariable UUID modelId, @PathVariable UUID stateId) {
        equipmentModelStateHourlyEarningsService.deleteEquipmentModelStateHourlyEarnings(modelId, stateId);
        return ResponseEntity.noContent().build();
    }
}
