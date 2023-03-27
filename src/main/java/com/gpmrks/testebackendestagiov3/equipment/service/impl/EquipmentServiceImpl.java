package com.gpmrks.testebackendestagiov3.equipment.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment.repository.EquipmentRepository;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;

    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentModelService equipmentModelService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentModelService = equipmentModelService;
    }

    @Override
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> getAllEquipmentsByModelId(UUID modelId) {
        List<Equipment> equipments = equipmentRepository.getEquipmentsByModelId(modelId);
        return equipments;
    }

    @Override
    public Equipment getEquipmentById(UUID id) {
        return checkIfEquipmentExists(id);
    }

    @Override
    public Equipment createEquipment(EquipmentForm equipmentToCreate) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentModel(equipmentModelService.getEquipModelById(equipmentToCreate.getEquipmentModelId()));
        equipment.setName(equipmentToCreate.getName());
        equipmentRepository.save(equipment);

        return equipment;
    }

    @Override
    public Equipment updateEquipment(UUID id, EquipmentForm updatedEquipment) {
        Equipment equipment = checkIfEquipmentExists(id);
        equipment.setName(updatedEquipment.getName());
        equipment.setEquipmentModel(equipmentModelService.getEquipModelById(updatedEquipment.getEquipmentModelId()));
        equipmentRepository.save(equipment);
        return equipment;
    }

    @Override
    public void deleteEquipment(UUID id) {
        checkIfEquipmentExists(id);
        equipmentRepository.deleteById(id);
    }

    private Equipment checkIfEquipmentExists(UUID id) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        final Equipment equipment;

        if (optionalEquipment.isPresent()) {
            equipment = optionalEquipment.get();
        } else {
            throw new EquipmentNotFoundException(id);
        }

        return equipment;
    }
}
