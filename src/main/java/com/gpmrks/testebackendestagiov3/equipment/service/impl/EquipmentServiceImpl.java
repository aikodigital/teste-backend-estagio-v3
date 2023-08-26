package com.gpmrks.testebackendestagiov3.equipment.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentForm;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment.repository.EquipmentRepository;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.EquipmentModelNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_model.repository.EquipmentModelRepository;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;
    private EquipmentModelRepository equipmentModelRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentModelRepository equipmentModelRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentModelRepository = equipmentModelRepository;
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
        Equipment equipment = returnEquipmentIfExistsOrElseThrowEquipmentNotFound(id);
        return new EquipmentDTO(equipment);
    }

    @Override
    public EquipmentDTO createEquipment(EquipmentForm equipmentToCreate) {
        Equipment equipment = new Equipment();
        EquipmentModel equipmentModel = returnEquipmentModelIfExistsOrElseThrowModelNotFound(equipmentToCreate.getEquipmentModelId());

        equipment.setName(equipmentToCreate.getName());
        equipment.setEquipmentModel(equipmentModel);

        Equipment equipmentSaved = equipmentRepository.save(equipment);
        return new EquipmentDTO(equipmentSaved);
    }

    @Override
    public EquipmentDTO updateEquipment(UUID id, EquipmentForm updatedEquipment) {
        Equipment equipment = returnEquipmentIfExistsOrElseThrowEquipmentNotFound(id);

        if (updatedEquipment.getName() == null) {
            equipment.setName(equipment.getName());
        } else {
            equipment.setName(updatedEquipment.getName());
        }

        if (updatedEquipment.getEquipmentModelId() == null) {
            equipment.setEquipmentModel(equipment.getEquipmentModel());
        } else {
            EquipmentModel equipmentModel = returnEquipmentModelIfExistsOrElseThrowModelNotFound(updatedEquipment.getEquipmentModelId());
            equipment.setEquipmentModel(equipmentModel);
        }

        Equipment equipmentUpdated = equipmentRepository.save(equipment);

        return new EquipmentDTO(equipmentUpdated);
    }

    @Override
    public void deleteEquipment(UUID id) {
        returnEquipmentIfExistsOrElseThrowEquipmentNotFound(id);
        equipmentRepository.deleteById(id);
    }

    private Equipment returnEquipmentIfExistsOrElseThrowEquipmentNotFound(UUID id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFoundException(id));
    }

    private EquipmentModel returnEquipmentModelIfExistsOrElseThrowModelNotFound(UUID modelId) {
        return equipmentModelRepository.findById(modelId).orElseThrow(() -> new EquipmentModelNotFoundException(modelId));
    }
}
