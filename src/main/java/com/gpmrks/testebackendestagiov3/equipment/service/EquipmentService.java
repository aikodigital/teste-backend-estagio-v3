package com.gpmrks.testebackendestagiov3.equipment.service;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;

import java.util.List;
import java.util.UUID;

public interface EquipmentService {

    List<EquipmentDTO> getAllEquipments();

    List<EquipmentDTO> getAllEquipmentsByModelId(UUID modelId);

    EquipmentDTO getEquipmentById(UUID id);

    EquipmentDTO createEquipment(EquipmentForm equipmentToCreate);

    EquipmentDTO updateEquipment(UUID id, EquipmentForm updatedEquipment);

    void deleteEquipment(UUID id);

}
