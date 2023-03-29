package com.desafio.aiko.services;

import com.desafio.aiko.models.entities.Equipment;
import com.desafio.aiko.models.entities.EquipmentModel;
import com.desafio.aiko.models.request.EquipmentRequest;
import com.desafio.aiko.repositories.EquipmentModelRepository;
import com.desafio.aiko.repositories.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@EnableTransactionManagement
@Slf4j
@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentModelRepository equipmentModelRepository;


    public EquipmentRequest toDto(Equipment equipment) {
        return new EquipmentRequest(UUID.randomUUID().toString(), equipment.getName(), equipment.getEquipmentModel().getId());
    }

    public void create(EquipmentRequest equipmentRequest) {
        try {
            Optional<EquipmentModel> equipmentModel = equipmentModelRepository.findById(equipmentRequest.getEquipmentModel());

            Equipment equipment = new Equipment();
            equipment.setId(UUID.randomUUID());
            equipment.setEquipmentModel(equipmentModel.get());
            equipment.setName(equipmentRequest.getName());

            equipmentRepository.save(equipment);

        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(UUID uuid) {
        try {
            if (equipmentRepository.findById(uuid).isEmpty()) throw new NullPointerException();
            equipmentRepository.deleteEquipmentAndRelatedEntities(uuid);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateEquipment(EquipmentRequest equipmentRequest) {
        try {
            Optional<EquipmentModel> equipmentModelId = equipmentModelRepository.findById(equipmentRequest.getEquipmentModel());

            Equipment equipment = Equipment.builder()
                    .id(UUID.fromString(equipmentRequest.getId()))
                    .name(equipmentRequest.getName())
                    .equipmentModel(EquipmentModel.builder()
                            .id(equipmentModelId.get().getId())
                            .name(equipmentRequest.getName())
                            .build())
                    .build();
            equipmentRepository.save(equipment);
        }catch (Exception e){
            throw  e;
        }
    }


    public List<EquipmentRequest> findAllEquipments() {
        List<Equipment> equipmentList = equipmentRepository.findAllEquipments();
        List<EquipmentRequest> equipmentRequests = equipmentList.stream().map(this::toDto).collect(Collectors.toList());
        if (!equipmentList.isEmpty()) return equipmentRequests;
        throw new NullPointerException();
    }
}
