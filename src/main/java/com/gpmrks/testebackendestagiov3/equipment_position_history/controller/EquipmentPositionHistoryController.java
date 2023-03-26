package com.gpmrks.testebackendestagiov3.equipment_position_history.controller;

import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import com.gpmrks.testebackendestagiov3.equipment_position_history.service.EquipmentPositionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("equipments-positions-histories")
public class EquipmentPositionHistoryController {

    private EquipmentPositionHistoryService equipmentPositionHistoryService;

    @Autowired
    public EquipmentPositionHistoryController(EquipmentPositionHistoryService equipmentPositionHistoryService) {
        this.equipmentPositionHistoryService = equipmentPositionHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentPositionHistoryDTO>> getAllEquipmentsPositionsHistories() {
        List<EquipmentPositionHistory> equipmentPositionHistories = equipmentPositionHistoryService.getAllEquipmentsPositionHistories();
        List<EquipmentPositionHistoryDTO> equipmentPositionHistoryDTOS = equipmentPositionHistories.stream()
                .map(EquipmentPositionHistory::equipmentPositionHistoryToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentPositionHistoryDTOS);
    }
}
