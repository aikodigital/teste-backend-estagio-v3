package com.gpmrks.testebackendestagiov3.equipment_state_history.service;

import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;

import java.util.List;
import java.util.UUID;

public interface EquipmentStateHistoryService {

    List<EquipmentStateHistoryDTO> getAllEquipmentsStateHistories();

    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByEquipId(UUID equipmentId);

    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByStateId(UUID stateId);

    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByEquipAndStateIds(UUID equipId, UUID stateId);

    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByDate(EquipmentHistoryDateForm date);

    EquipmentStateHistoryDTO registerEquipmentState(UUID equipmentId, UUID stateId);

    void deleteEquipmentStateHistory(UUID equipmentId);

}
