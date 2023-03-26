package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.controller;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service.EquipmentModelStateHourlyEarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("equipments-models-states-hourly-earnings")
public class EquipmentModelStateHourlyEarningsController {

    private EquipmentModelStateHourlyEarningsService equipmentModelStateHourlyEarningsService;

    @Autowired
    public EquipmentModelStateHourlyEarningsController(EquipmentModelStateHourlyEarningsService equipmentModelStateHourlyEarningsService) {
        this.equipmentModelStateHourlyEarningsService = equipmentModelStateHourlyEarningsService;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentModelStateHourlyEarningsDTO>> getAllEquipmentsModelsStatesHourlyEarnings() {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList = equipmentModelStateHourlyEarningsService.getAllEquipmentsModelStateHourlyEarnings();
        List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsDTOS = equipmentModelStateHourlyEarningsList.stream()
                .map(EquipmentModelStateHourlyEarnings::equipmentModelStateHourlyEarningsToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsDTOS);
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<EquipmentModelStateHourlyEarningsDTO>> getEquipmentsModelsStatesHourlyEarningsByModelId(@PathVariable UUID modelId) {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsByModelId = equipmentModelStateHourlyEarningsService.getEquipmentModelStateHourlyEarningsByModelId(modelId);
        List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsByModelIdDTOS = equipmentModelStateHourlyEarningsByModelId.stream()
                .map(EquipmentModelStateHourlyEarnings::equipmentModelStateHourlyEarningsToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsByModelIdDTOS);
    }

    @GetMapping("/state/{stateId}")
    public ResponseEntity<List<EquipmentModelStateHourlyEarningsDTO>> getEquipmentsModelsStatesHourlyEarningsByStateId(@PathVariable UUID stateId) {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsByStateId = equipmentModelStateHourlyEarningsService.getEquipmentModelStateHourlyEarningsByStateId(stateId);
        List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsByStateIdDTOS = equipmentModelStateHourlyEarningsByStateId.stream()
                .map(EquipmentModelStateHourlyEarnings::equipmentModelStateHourlyEarningsToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsByStateIdDTOS);
    }

    @GetMapping("/{modelId}/{stateId}")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> getEquipmentsModelsStatesHourlyEarningsByModelAndStateIds(@PathVariable UUID modelId, @PathVariable UUID stateId) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsService.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsByStateIdDTO = equipmentModelStateHourlyEarnings.equipmentModelStateHourlyEarningsToDTO();
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsByStateIdDTO);
    }

    @PostMapping("{modelId}/{stateId}")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> createEquipmentModelStateHourlyEarnings(@PathVariable UUID modelId, @PathVariable UUID stateId, @RequestBody EquipmentModelStateHourlyEarningsForm value, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsService.createEquipmentModelStateHourlyEarnings(modelId, stateId, value);
        EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsDTO = equipmentModelStateHourlyEarnings.equipmentModelStateHourlyEarningsToDTO();
        URI uri = uriComponentsBuilder.path("equipments-models-states-hourly-earnings/{modelId}/{stateId}").buildAndExpand(equipmentModelStateHourlyEarnings.getEquipmentModel().getId(), equipmentModelStateHourlyEarnings.getEquipmentState().getId()).toUri();
        return ResponseEntity.created(uri).body(equipmentModelStateHourlyEarningsDTO);
    }

    @PutMapping("{modelId}/{stateId}")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> updateEquipmentModelStateHourlyEarnings(@PathVariable UUID modelId, @PathVariable UUID stateId, @RequestBody EquipmentModelStateHourlyEarningsForm value) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsService.updateEquipmentModelStateHourlyEarnings(modelId, stateId, value);
        EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsDTO = equipmentModelStateHourlyEarnings.equipmentModelStateHourlyEarningsToDTO();
        return ResponseEntity.ok().body(equipmentModelStateHourlyEarningsDTO);
    }

    @DeleteMapping("{modelId}/{stateId}")
    public ResponseEntity<Void> deleteEquipmentModelStateHourlyEarnings(@PathVariable UUID modelId, @PathVariable UUID stateId) {
        equipmentModelStateHourlyEarningsService.deleteEquipmentModelStateHourlyEarnings(modelId, stateId);
        return ResponseEntity.noContent().build();
    }
}
