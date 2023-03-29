package com.api.aikobackendteste.services;


import com.api.aikobackendteste.models.EquipmentModel;
import com.api.aikobackendteste.repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Transactional
    public EquipmentModel save(EquipmentModel equipmentModel){
        return equipmentRepository.save(equipmentModel);
    }
    public List<EquipmentModel> getAll(){
        return equipmentRepository.findAll();
    }
    public EquipmentModel get(UUID id){
        Optional<EquipmentModel> equipmentModel=equipmentRepository.findById(id);
        if(equipmentModel.isPresent()){
            return equipmentModel.get();
        }
        throw new RuntimeException("O equipamento com id " + id + " não foi encontrado.");
    }
    public String delete(UUID id){
        if(!equipmentRepository.existsById(id))
            return "equipamento nao encontrado";
        equipmentRepository.deleteById(id);
        return "equipamento deletado com sucesso!";
    }
    public EquipmentModel update(UUID id, EquipmentModel equipmentModel){
        if(!equipmentRepository.existsById(id)){
            throw new IllegalArgumentException("Equipamento não encontrado");
        }
        return equipmentRepository.save(equipmentModel);
    }
}
