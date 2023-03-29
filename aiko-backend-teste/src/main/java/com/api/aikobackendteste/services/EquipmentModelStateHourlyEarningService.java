package com.api.aikobackendteste.services;

import com.api.aikobackendteste.models.EquipmentModelModel;
import com.api.aikobackendteste.models.EquipmentModelStateHourlyEarningModel;
import com.api.aikobackendteste.models.EquipmentStateModel;
import com.api.aikobackendteste.primaryKeys.EquipmentModelStateHourlyEarningPK;
import com.api.aikobackendteste.repositories.EquipmentModelRepository;
import com.api.aikobackendteste.repositories.EquipmentModelStateHourlyEarningRepository;
import com.api.aikobackendteste.repositories.EquipmentStateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EquipmentModelStateHourlyEarningService {
    @Autowired
    EquipmentModelStateHourlyEarningRepository equipmentModelStateHourlyEarningRepository;
    @Autowired
    EquipmentModelRepository equipmentModelRepository;
    @Autowired
    EquipmentStateRepository equipmentStateRepository;
    @Transactional
    public EquipmentModelStateHourlyEarningModel save(EquipmentModelStateHourlyEarningModel equipmentModelStateHourlyEarningModel){
        return equipmentModelStateHourlyEarningRepository.save(equipmentModelStateHourlyEarningModel);
    }
    public EquipmentModelStateHourlyEarningModel get(UUID modelId, UUID stateID){
        if(!equipmentModelRepository.existsById(modelId)){
            throw new IllegalArgumentException("modelo não encontrado");
        }
        if(!equipmentStateRepository.existsById(stateID)){
            throw new IllegalArgumentException("estado não encontrado");
        }
        EquipmentModelModel equipmentModel=equipmentModelRepository.findById(modelId).get();
        EquipmentStateModel equipmentState=equipmentStateRepository.findById(stateID).get();
        EquipmentModelStateHourlyEarningPK id=new EquipmentModelStateHourlyEarningPK(equipmentModel,equipmentState);
        throw new RuntimeException("O ganho com id " + id + " não foi encontrado.");
    }
    public String delete(UUID modelId,UUID stateId){
        if(!equipmentModelRepository.existsById(modelId)){
            return "O modelo com id " + modelId + " não foi encontrado.";
        }
        if(!equipmentStateRepository.existsById(stateId)){
            return "O estado com id " + stateId + " não foi encontrado.";
        }
        EquipmentModelModel equipmentModel=equipmentModelRepository.findById(modelId).get();
        EquipmentStateModel equipmentState=equipmentStateRepository.findById(stateId).get();
        EquipmentModelStateHourlyEarningPK id=new EquipmentModelStateHourlyEarningPK(equipmentModel,equipmentState);
        equipmentModelStateHourlyEarningRepository.deleteById(id);
        return "O ganho com id " + id + " foi deletado com sucesso!.";
    }
    public EquipmentModelStateHourlyEarningModel update(EquipmentModelStateHourlyEarningModel equipmentModelStateHourlyEarningModel){
        if(!equipmentModelRepository.existsById(equipmentModelStateHourlyEarningModel.getEquipmentModelModel().getId())){
            throw new IllegalArgumentException("modelo não encontrado");
        }
        if(!equipmentStateRepository.existsById(equipmentModelStateHourlyEarningModel.getEquipmentStateModel().getId())){
            throw new IllegalArgumentException("estado não encontrado");
        }
        EquipmentModelModel equipmentModel=equipmentModelRepository.findById(equipmentModelStateHourlyEarningModel.getEquipmentModelModel().getId()).get();
        EquipmentStateModel equipmentState=equipmentStateRepository.findById(equipmentModelStateHourlyEarningModel.getEquipmentStateModel().getId()).get();
        EquipmentModelStateHourlyEarningPK id=new EquipmentModelStateHourlyEarningPK(equipmentModel,equipmentState);
        equipmentModelStateHourlyEarningRepository.deleteById(id);

        if(equipmentModelStateHourlyEarningRepository.findById(id).isEmpty())
        return equipmentModelStateHourlyEarningModel;
        throw new IllegalArgumentException("ganho não encontrado");

    }
}
