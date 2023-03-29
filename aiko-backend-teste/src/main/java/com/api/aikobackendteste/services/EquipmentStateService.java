package com.api.aikobackendteste.services;

import com.api.aikobackendteste.models.EquipmentStateModel;
import com.api.aikobackendteste.repositories.EquipmentStateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentStateService {
    @Autowired
    EquipmentStateRepository equipmentStateRepository;
    @Transactional
    public EquipmentStateModel save(EquipmentStateModel equipmentStateModel){
        return equipmentStateRepository.save(equipmentStateModel);
    }
    public EquipmentStateModel get(UUID id){
        Optional<EquipmentStateModel> equipmentStateModel=equipmentStateRepository.findById(id);
        if(equipmentStateModel.isPresent()){
            return equipmentStateModel.get();
        }
        throw new RuntimeException("O estado com id " + id + " não foi encontrado.");
    }
    public String delete(UUID id){
        if(!equipmentStateRepository.existsById(id)){
            return "estado não encontrado";
        }
        equipmentStateRepository.deleteById(id);
        return "estado deletado com sucesso";
    }
    public EquipmentStateModel update(EquipmentStateModel equipmentStateModel){
        if(!equipmentStateRepository.existsById(equipmentStateModel.getId()))
            throw new IllegalArgumentException("estado não encontrado");
        return equipmentStateRepository.save(equipmentStateModel);
    }
}
