package com.gpmrks.testebackendestagiov3.equipment_model.service.impl;

import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelDTO;
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
import java.util.UUID;

@Service
public class EquipmentModelServiceImpl implements EquipmentModelService {

    private EquipmentModelRepository equipmentModelRepository;

    @Autowired
    public EquipmentModelServiceImpl(EquipmentModelRepository equipmentModelRepository) {
        this.equipmentModelRepository = equipmentModelRepository;
    }

    @Override
    public List<EquipmentModelDTO> getAllEquipmentsModels() {
        List<EquipmentModel> equipmentModelList = equipmentModelRepository.findAll();
        return equipmentModelList.stream().map(EquipmentModelDTO::new).toList();
    }

    @Override
    public EquipmentModelDTO getEquipModelById(UUID id) {
        EquipmentModel equipmentModel = returnEquipmentModelOrElseThrowModelNotFound(id);
        return new EquipmentModelDTO(equipmentModel);
    }

    @Override
    public EquipmentModelDTO createEquipmentModel(EquipmentModelForm equipmentModelToCreate) {
        EquipmentModel equipmentModel = new EquipmentModel();
        equipmentModel.setName(equipmentModelToCreate.getName());
        EquipmentModel equipmentModelSaved = equipmentModelRepository.save(equipmentModel);
        return new EquipmentModelDTO(equipmentModelSaved);
    }

    @Override
    public EquipmentModelDTO updateEquipmentModel(UUID id, EquipmentModelForm updatedEquipmentModel) {
        EquipmentModel equipmentModel = returnEquipmentModelOrElseThrowModelNotFound(id);
        equipmentModel.setName(updatedEquipmentModel.getName());
        EquipmentModel equipmentModelUpdated = equipmentModelRepository.save(equipmentModel);
        return new EquipmentModelDTO(equipmentModelUpdated);
    }

    @Override
    public void deleteEquipmentModel(UUID id) {
        try {
        returnEquipmentModelOrElseThrowModelNotFound(id);
        equipmentModelRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new CannotDeleteEquipmentModelException(id);
        }
    }

    private EquipmentModel returnEquipmentModelOrElseThrowModelNotFound(UUID id){
        return equipmentModelRepository.findById(id).orElseThrow(() -> new EquipmentModelNotFoundException(id));
    }
}
