package com.gpmrks.testebackendestagiov3.equipment_state.controller;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateForm;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipments-states")
public class EquipmentStateController {

    private EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentStateController(EquipmentStateService equipmentStateService) {
        this.equipmentStateService = equipmentStateService;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentStateDTO>> getAllEquipmentsStates() {
        List<EquipmentState> equipmentStates = equipmentStateService.getAllEquipmentsStates();
        List<EquipmentStateDTO> equipmentStateDTOS = equipmentStates.stream()
                .map(EquipmentState::equipmentStateToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentStateDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentStateDTO> getEquipmentStateById(@PathVariable UUID id) {
        EquipmentState equipmentState = equipmentStateService.getEquipmentStateById(id);
        EquipmentStateDTO equipmentStateDTO = equipmentState.equipmentStateToDTO();
        return ResponseEntity.ok().body(equipmentStateDTO);
    }

    @PostMapping
    public ResponseEntity<EquipmentStateDTO> createEquipmentState(@RequestBody EquipmentStateForm equipmentStateToCreate, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentState equipmentState = equipmentStateService.createEquipmentState(equipmentStateToCreate);
        EquipmentStateDTO equipmentStateDTO = equipmentState.equipmentStateToDTO();
        URI uri = uriComponentsBuilder.path("/equipments-states/{id}").buildAndExpand(equipmentState.getId()).toUri();
        return ResponseEntity.created(uri).body(equipmentStateDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<EquipmentStateDTO> updateEquipmentState(@PathVariable UUID id, @RequestBody EquipmentStateForm updatedEquipmentState) {
        EquipmentState equipmentState = equipmentStateService.updateEquipmentState(id, updatedEquipmentState);
        EquipmentStateDTO equipmentStateDTO = equipmentState.equipmentStateToDTO();
        return ResponseEntity.ok().body(equipmentStateDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEquipmentState(@PathVariable UUID id) {
        equipmentStateService.deleteEquipmentState(id);
        return ResponseEntity.noContent().build();
    }
}
