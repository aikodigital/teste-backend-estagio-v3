package com.gpmrks.testebackendestagiov3.equipment_state.service;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateForm;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;

import java.util.List;
import java.util.UUID;

public interface EquipmentStateService {

    List<EquipmentState> getAllEquipmentsStates();

    EquipmentState getEquipmentStateById(UUID id);

    EquipmentState createEquipmentState(EquipmentStateForm equipmentStateToCreate);

    EquipmentState updateEquipmentState(UUID id, EquipmentStateForm updatedEquipmentState);

    void deleteEquipmentState(UUID id);

}
