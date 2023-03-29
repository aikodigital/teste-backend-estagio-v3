package com.api.aikobackendteste.services;

import com.api.aikobackendteste.models.EquipmentModelModel;
import com.api.aikobackendteste.repositories.EquipmentModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentModelService {
    @Autowired
    EquipmentModelRepository equipmentModelRepository;
    @Transactional
    public EquipmentModelModel save(EquipmentModelModel equipmentModelModel){
        return equipmentModelRepository.save(equipmentModelModel);
    }
    public List<EquipmentModelModel> getAll(){
        return equipmentModelRepository.findAll();
    }
    public EquipmentModelModel get(UUID id){
        Optional<EquipmentModelModel> equipmentModelModel=equipmentModelRepository.findById(id);
        if(equipmentModelModel.isPresent()){
            return equipmentModelModel.get();
        }
        throw new RuntimeException("O modelo com id " + id + " não foi encontrado.");
    }
    public String delete(UUID id){
        if(!equipmentModelRepository.existsById(id)){
            return "modelo não encontrado";
        }
        equipmentModelRepository.deleteById(id);
        return "modelo deletado com sucesso";
    }
    public EquipmentModelModel update(EquipmentModelModel equipmentModelModel,UUID id){
        if(!equipmentModelRepository.existsById(id))
            throw new IllegalArgumentException("modelo não encontrado");
        return equipmentModelRepository.save(equipmentModelModel);
    }
}
