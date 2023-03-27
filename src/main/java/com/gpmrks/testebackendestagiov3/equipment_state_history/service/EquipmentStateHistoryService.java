package com.gpmrks.testebackendestagiov3.equipment_state_history.service;

import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;

import java.util.List;
import java.util.UUID;

public interface EquipmentStateHistoryService {

    List<EquipmentStateHistory> getAllEquipmentsStateHistories();

    List<EquipmentStateHistory> getEquipmentStateHistoryByEquipId(UUID equipmentId);

    List<EquipmentStateHistory> getEquipmentStateHistoryByStateId(UUID stateId);

    List<EquipmentStateHistory> getEquipmentStateHistoryByEquipAndStateIds(UUID equipId, UUID stateId);

    List<EquipmentStateHistory> getEquipmentStateHistoryByDate(EquipmentHistoryDateForm date);

    EquipmentStateHistory registerEquipmentState(UUID equipmentId, UUID stateId);

    void deleteEquipmentStateHistory(UUID equipmentId);

}
