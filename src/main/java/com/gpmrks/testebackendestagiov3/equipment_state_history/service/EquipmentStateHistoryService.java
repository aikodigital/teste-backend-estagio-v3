package com.gpmrks.testebackendestagiov3.equipment_state_history.service;

import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EquipmentStateHistoryService {

    @Transactional(readOnly = true)
    List<EquipmentStateHistoryDTO> getAllEquipmentsStateHistories();

    @Transactional(readOnly = true)
    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByEquipId(UUID equipmentId);

    @Transactional(readOnly = true)
    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByStateId(UUID stateId);

    @Transactional(readOnly = true)
    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByEquipAndStateIds(UUID equipId, UUID stateId);

    @Transactional(readOnly = true)
    List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByDate(EquipmentHistoryDateForm date);

    @Transactional
    EquipmentStateHistoryDTO registerEquipmentState(UUID equipmentId, UUID stateId);

    @Transactional
    void deleteEquipmentStateHistory(UUID equipmentId);

}
