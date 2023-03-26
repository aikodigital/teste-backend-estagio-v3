package com.gpmrks.testebackendestagiov3.equipment.service;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;

import java.util.List;
import java.util.UUID;

public interface EquipmentService {

    List<Equipment> getAllEquipments();

    List<Equipment> getAllEquipmentsByModelId(UUID modelId);

    Equipment getEquipmentById(UUID id);

    Equipment createEquipment(EquipmentForm equipmentToCreate);

    Equipment updateEquipment(UUID id, EquipmentForm updatedEquipment);

    void deleteEquipment(UUID id);

}
