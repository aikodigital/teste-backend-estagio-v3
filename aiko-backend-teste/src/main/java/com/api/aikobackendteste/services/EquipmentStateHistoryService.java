package com.api.aikobackendteste.services;

import com.api.aikobackendteste.models.EquipmentModel;
import com.api.aikobackendteste.models.EquipmentStateHistoryModel;
import com.api.aikobackendteste.primaryKeys.EquipmentStateHistoryPk;
import com.api.aikobackendteste.repositories.EquipmentRepository;
import com.api.aikobackendteste.repositories.EquipmentStateHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentStateHistoryService {
    @Autowired
    EquipmentStateHistoryRepository equipmentStateHistoryRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Transactional
    public EquipmentStateHistoryModel save(EquipmentStateHistoryModel equipmentStateHistoryModel){
        return equipmentStateHistoryRepository.save(equipmentStateHistoryModel);
    }
    public EquipmentStateHistoryModel get(UUID equipmentId, Date date){
        if(!equipmentRepository.existsById(equipmentId)){
            throw new IllegalArgumentException("id do equipamento não encontrado");
        }
        EquipmentModel equipment=equipmentRepository.findById(equipmentId).get();
        EquipmentStateHistoryPk id=new EquipmentStateHistoryPk(equipment,date);
        Optional<EquipmentStateHistoryModel> equipmentStateHistoryModel = equipmentStateHistoryRepository.findById(id);


        if(equipmentStateHistoryModel.isPresent()){
            return equipmentStateHistoryModel.get();
        }
        throw new RuntimeException("O historico com id " + id + " não foi encontrado.");
    }
    public String delete(UUID equipmentId, Date date){
        if(!equipmentRepository.existsById(equipmentId)){
            throw new IllegalArgumentException("id do equipamento não encontrado");
        }
        EquipmentModel equipment=equipmentRepository.findById(equipmentId).get();
        EquipmentStateHistoryPk id=new EquipmentStateHistoryPk(equipment,date);
        equipmentStateHistoryRepository.deleteById(id);
        return "historico deletado com sucesso";
    }
    public EquipmentStateHistoryModel update(EquipmentStateHistoryModel equipmentStateHistoryModel){ if(!equipmentRepository.existsById(equipmentStateHistoryModel.getEquipmentModel().getId())){
        throw new IllegalArgumentException("id do equipamento não encontrado");
    }
        EquipmentModel equipment=equipmentRepository.findById(equipmentStateHistoryModel.getEquipmentModel().getId()).get();
        EquipmentStateHistoryPk id=new EquipmentStateHistoryPk(equipment,equipmentStateHistoryModel.getDate());

        if(!equipmentStateHistoryRepository.existsById(id))
            throw new IllegalArgumentException("historico não encontrado");
        return equipmentStateHistoryRepository.save(equipmentStateHistoryModel);
    }
}
