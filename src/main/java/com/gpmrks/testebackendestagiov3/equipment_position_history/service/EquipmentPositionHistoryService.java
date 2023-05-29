package com.gpmrks.testebackendestagiov3.equipment_position_history.service;

import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionForm;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;

import java.util.List;
import java.util.UUID;

public interface EquipmentPositionHistoryService {

    List<EquipmentPositionHistoryDTO> getAllEquipmentsPositionHistories();

    List<EquipmentPositionHistoryDTO> getEquipmentPositionHistoryByEquipId(UUID equipmentId);

    List<EquipmentPositionHistoryDTO> getEquipmentPositionHistoryByDate(EquipmentHistoryDateForm equipmentHistoryDateForm);

    EquipmentPositionHistoryDTO registerEquipmentPosition(UUID equipmentId, EquipmentPositionForm equipmentPositionToCreate);

    void deleteEquipmentPositionHistory(UUID equipmentId);

}
