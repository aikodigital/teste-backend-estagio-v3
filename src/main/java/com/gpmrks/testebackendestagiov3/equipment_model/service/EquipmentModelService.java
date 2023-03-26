package com.gpmrks.testebackendestagiov3.equipment_model.service;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelForm;

import java.util.List;
import java.util.UUID;

public interface EquipmentModelService {

    List<EquipmentModel> getAllEquipmentsModels();

    EquipmentModel getEquipModelById(UUID id);

    EquipmentModel createEquipmentModel(EquipmentModelForm equipmentModelToCreate);

    EquipmentModel updateEquipmentModel(UUID id, EquipmentModelForm updatedEquipmentModel);

    void deleteEquipmentModel(UUID id);

}
