package com.gpmrks.testebackendestagiov3.equipment_position_history.service;

import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionForm;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EquipmentPositionHistoryService {

    @Transactional(readOnly = true)
    List<EquipmentPositionHistoryDTO> getAllEquipmentsPositionHistories();

    @Transactional(readOnly = true)
    List<EquipmentPositionHistoryDTO> getEquipmentPositionHistoryByEquipId(UUID equipmentId);

    @Transactional(readOnly = true)
    List<EquipmentPositionHistoryDTO> getEquipmentPositionHistoryByDate(EquipmentHistoryDateForm equipmentHistoryDateForm);

    @Transactional
    EquipmentPositionHistoryDTO registerEquipmentPosition(UUID equipmentId, EquipmentPositionForm equipmentPositionToCreate);

    @Transactional
    void deleteEquipmentPositionHistory(UUID equipmentId);

}
