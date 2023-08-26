package com.gpmrks.testebackendestagiov3.equipment_state.service;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EquipmentStateService {

    @Transactional(readOnly = true)
    List<EquipmentStateDTO> getAllEquipmentsStates();

    @Transactional(readOnly = true)
    EquipmentStateDTO getEquipmentStateById(UUID id);

    @Transactional
    EquipmentStateDTO createEquipmentState(EquipmentStateForm equipmentStateToCreate);

    @Transactional
    EquipmentStateDTO updateEquipmentState(UUID id, EquipmentStateForm updatedEquipmentState);

    @Transactional
    void deleteEquipmentState(UUID id);

}
