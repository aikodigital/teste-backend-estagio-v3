package com.gpmrks.testebackendestagiov3.equipment_state_history.controller;

import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistoryId;
import com.gpmrks.testebackendestagiov3.equipment_state_history.service.EquipmentStateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("equipments-states-histories")
public class EquipmentStateHistoryController {

    private EquipmentStateHistoryService equipmentStateHistoryService;

    @Autowired
    public EquipmentStateHistoryController(EquipmentStateHistoryService equipmentStateHistoryService) {
        this.equipmentStateHistoryService = equipmentStateHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentStateHistoryDTO>> getAllEquipmentsStateHistories() {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.getAllEquipmentsStateHistories();
        List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS = equipmentStateHistories.stream()
                .map(EquipmentStateHistory::equipmentStateHistoryDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentStateHistoryDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentStateHistoryDTO> getEquipmentStateHistoryById(@PathVariable EquipmentStateHistoryId id) {
        Optional<EquipmentStateHistory> equipmentStateHistory = equipmentStateHistoryService.getEquipmentStateHistoryById(id);
        EquipmentStateHistoryDTO equipmentStateHistoryDTO = equipmentStateHistory.get().equipmentStateHistoryDTO();
        return ResponseEntity.ok().body(equipmentStateHistoryDTO);
    }
}
