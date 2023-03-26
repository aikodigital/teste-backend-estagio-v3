package com.gpmrks.testebackendestagiov3.equipment_model.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelForm;
import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.CannotDeleteEquipmentModelException;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.EquipmentModelNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_model.repository.EquipmentModelRepository;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentModelServiceImpl implements EquipmentModelService {

    private EquipmentModelRepository equipmentModelRepository;

    @Autowired
    public EquipmentModelServiceImpl(EquipmentModelRepository equipmentModelRepository) {
        this.equipmentModelRepository = equipmentModelRepository;
    }

    @Override
    public List<EquipmentModel> getAllEquipmentsModels() {
        return equipmentModelRepository.findAll();
    }

    @Override
    public EquipmentModel getEquipModelById(UUID id) {
        return checkIfEquipmentModelExists(id);
    }

    @Override
    public EquipmentModel createEquipmentModel(EquipmentModelForm equipmentModelToCreate) {
        EquipmentModel equipmentModel = new EquipmentModel();
        equipmentModel.setName(equipmentModelToCreate.getName());
        equipmentModelRepository.save(equipmentModel);
        return equipmentModel;
    }

    @Override
    public EquipmentModel updateEquipmentModel(UUID id, EquipmentModelForm updatedEquipmentModel) {
        EquipmentModel equipmentModel = checkIfEquipmentModelExists(id);
        equipmentModel.setName(updatedEquipmentModel.getName());
        equipmentModelRepository.save(equipmentModel);
        return equipmentModel;
    }

    @Override
    public void deleteEquipmentModel(UUID id) {
        try {
        EquipmentModel equipmentModel = checkIfEquipmentModelExists(id);
        equipmentModelRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new CannotDeleteEquipmentModelException(id);
        }
    }

    private EquipmentModel checkIfEquipmentModelExists(UUID id){
        Optional<EquipmentModel> optionalEquipmentModel = equipmentModelRepository.findById(id);
        final EquipmentModel equipmentModel;

        if (optionalEquipmentModel.isPresent()) {
            equipmentModel = optionalEquipmentModel.get();
        } else {
            throw new EquipmentModelNotFoundException(id);
        }

        return equipmentModel;
    }
}
