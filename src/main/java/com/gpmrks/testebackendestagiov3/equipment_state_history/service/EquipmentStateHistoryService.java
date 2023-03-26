package com.gpmrks.testebackendestagiov3.equipment_state_history.service;

import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistoryId;

import java.util.List;
import java.util.Optional;

public interface EquipmentStateHistoryService {

    List<EquipmentStateHistory> getAllEquipmentsStateHistories();

    Optional<EquipmentStateHistory> getEquipmentStateHistoryById(EquipmentStateHistoryId id);
}
