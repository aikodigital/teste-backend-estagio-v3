package com.gpmrks.testebackendestagiov3.equipment_model.service;

import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelDTO;
import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EquipmentModelService {

    @Transactional(readOnly = true)
    List<EquipmentModelDTO> getAllEquipmentsModels();

    @Transactional(readOnly = true)
    EquipmentModelDTO getEquipModelById(UUID id);

    @Transactional
    EquipmentModelDTO createEquipmentModel(EquipmentModelForm equipmentModelToCreate);

    @Transactional
    EquipmentModelDTO updateEquipmentModel(UUID id, EquipmentModelForm updatedEquipmentModel);

    @Transactional
    void deleteEquipmentModel(UUID id);

}
