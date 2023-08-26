package com.gpmrks.testebackendestagiov3.equipment.service;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EquipmentService {

    @Transactional(readOnly = true)
    List<EquipmentDTO> getAllEquipments();

    @Transactional(readOnly = true)
    List<EquipmentDTO> getAllEquipmentsByModelId(UUID modelId);

    @Transactional(readOnly = true)
    EquipmentDTO getEquipmentById(UUID id);

    @Transactional
    EquipmentDTO createEquipment(EquipmentForm equipmentToCreate);

    @Transactional
    EquipmentDTO updateEquipment(UUID id, EquipmentForm updatedEquipment);

    @Transactional
    void deleteEquipment(UUID id);

}
