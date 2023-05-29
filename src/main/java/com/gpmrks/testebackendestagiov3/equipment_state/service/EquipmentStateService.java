package com.gpmrks.testebackendestagiov3.equipment_state.service;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateForm;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;

import java.util.List;
import java.util.UUID;

public interface EquipmentStateService {

    List<EquipmentStateDTO> getAllEquipmentsStates();

    EquipmentStateDTO getEquipmentStateById(UUID id);

    EquipmentStateDTO createEquipmentState(EquipmentStateForm equipmentStateToCreate);

    EquipmentStateDTO updateEquipmentState(UUID id, EquipmentStateForm updatedEquipmentState);

    void deleteEquipmentState(UUID id);

}
