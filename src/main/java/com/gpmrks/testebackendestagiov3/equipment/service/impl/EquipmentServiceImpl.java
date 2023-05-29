package com.gpmrks.testebackendestagiov3.equipment.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment.repository.EquipmentRepository;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.repository.EquipmentModelRepository;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;
    private EquipmentModelRepository equipmentModelRepository;
    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentModelRepository equipmentModelRepository, EquipmentModelService equipmentModelService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentModelRepository = equipmentModelRepository;
        this.equipmentModelService = equipmentModelService;
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return equipmentList.stream().map(EquipmentDTO::new).toList();
    }

    @Override
    public List<EquipmentDTO> getAllEquipmentsByModelId(UUID modelId) {
        List<Equipment> equipments = equipmentRepository.getEquipmentsByModelId(modelId);
        return equipments.stream().map(EquipmentDTO::new).toList();
    }

    @Override
    public EquipmentDTO getEquipmentById(UUID id) {
        Equipment equipment = checkIfEquipmentExists(id);
        return new EquipmentDTO(equipment);
    }

    @Override
    public EquipmentDTO createEquipment(EquipmentForm equipmentToCreate) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentModel(equipmentModelRepository.findById(equipmentToCreate.getEquipmentModelId()).get());
        equipment.setName(equipmentToCreate.getName());
        Equipment equipmentSaved = equipmentRepository.save(equipment);
        return new EquipmentDTO(equipmentSaved);
    }

    @Override
    public EquipmentDTO updateEquipment(UUID id, EquipmentForm updatedEquipment) {
        Equipment equipment = checkIfEquipmentExists(id);
        equipment.setName(updatedEquipment.getName());
        equipment.setEquipmentModel(equipmentModelRepository.findById(updatedEquipment.getEquipmentModelId()).get());
        Equipment equipmentUpdated = equipmentRepository.save(equipment);
        return new EquipmentDTO(equipmentUpdated);
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
