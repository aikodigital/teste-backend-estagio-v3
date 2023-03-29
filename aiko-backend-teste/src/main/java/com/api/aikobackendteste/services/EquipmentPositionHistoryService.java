package com.api.aikobackendteste.services;


import com.api.aikobackendteste.models.EquipmentModel;
import com.api.aikobackendteste.models.EquipmentPositionHistoryModel;
import com.api.aikobackendteste.primaryKeys.EquipmentPositionHistoryPk;
import com.api.aikobackendteste.repositories.EquipmentPositionHistoryRepository;
import com.api.aikobackendteste.repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentPositionHistoryService {
    @Autowired
    EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Transactional
    public EquipmentPositionHistoryModel save(EquipmentPositionHistoryModel equipmentPositionHistoryModel){
        return equipmentPositionHistoryRepository.save(equipmentPositionHistoryModel);
    }
    public EquipmentPositionHistoryModel get(Date date, UUID equipmentId){
        if(!equipmentRepository.existsById(equipmentId)){
            throw new IllegalArgumentException("id do equipamento não encontrado");
        }
        EquipmentModel equipment=equipmentRepository.findById(equipmentId).get();
        EquipmentPositionHistoryPk id=new EquipmentPositionHistoryPk(equipment,date);
        Optional<EquipmentPositionHistoryModel> equipmentPositionHistoryModel = equipmentPositionHistoryRepository.findById(id);
        if(equipmentPositionHistoryModel.isPresent()){
            return equipmentPositionHistoryModel.get();
        }
        throw new RuntimeException("O historico com id " + id + " não foi encontrado.");
    }
    public String delete(UUID equipmentId,Date date){
        if(!equipmentRepository.existsById(equipmentId)){
            throw new IllegalArgumentException("id do equipamento não encontrado");
        }
        EquipmentModel equipment=equipmentRepository.findById(equipmentId).get();
        EquipmentPositionHistoryPk id=new EquipmentPositionHistoryPk(equipment,date);
        if(!equipmentPositionHistoryRepository.existsById(id)){
            return "historico não encontrado";
        }
        equipmentPositionHistoryRepository.deleteById(id);
        return "historico deletado com sucesso";
    }
    public EquipmentPositionHistoryModel update(EquipmentPositionHistoryModel equipmentPositionHistoryModel){
        if(!equipmentRepository.existsById(equipmentPositionHistoryModel.getEquipmentModel().getId())){
            throw new IllegalArgumentException("id do equipamento não encontrado");
        }
        EquipmentModel equipment=equipmentRepository.findById(equipmentPositionHistoryModel.getEquipmentModel().getId()).get();
        EquipmentPositionHistoryPk id=new EquipmentPositionHistoryPk(equipment,equipmentPositionHistoryModel.getDate());

        if(!equipmentPositionHistoryRepository.existsById(id))
            throw new IllegalArgumentException("historico não encontrado");
        return equipmentPositionHistoryRepository.save(equipmentPositionHistoryModel);
    }
}
