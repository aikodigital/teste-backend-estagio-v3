package com.desafio.aiko.services;

import com.desafio.aiko.models.entities.EquipmentModel;
import com.desafio.aiko.models.request.EquipmentModelRequest;
import com.desafio.aiko.repositories.EquipmentModelRepository;
import com.desafio.aiko.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquipmentModelService {
    @Autowired
    EquipmentModelRepository equipmentModelRepository;

    public EquipmentModelRequest toDto(EquipmentModel equipmentModel) {
        return new EquipmentModelRequest(String.valueOf(UUID.randomUUID()), equipmentModel.getName());
    }

    public void create(EquipmentModelRequest equipmentModelRequest) {
        try {
            EquipmentModel equipmentModel = EquipmentModel.builder().id(UUID.randomUUID()).name(equipmentModelRequest.getName()).build();
            equipmentModelRepository.save(equipmentModel);
        }catch (Exception e){
            throw e;
        }


    }

    public List<EquipmentModelRequest> findAll() {
        List<EquipmentModel> equipmentModelsList = equipmentModelRepository.findAllEquipmentModels();
        List<EquipmentModelRequest> equipmentRequests = equipmentModelsList.stream().map(this::toDto).collect(Collectors.toList());
        if (!equipmentModelsList.isEmpty()) return equipmentRequests;
        throw new NullPointerException();
    }

    public void delete(UUID uuid) {
        try {
            if (equipmentModelRepository.findById(uuid).isEmpty()) throw new NullPointerException();
            equipmentModelRepository.deleteEquipmentPositionHistoryByEquipmentModelId(uuid);
            equipmentModelRepository.deleteEquipmentStateHistoryByEquipmentModelId(uuid);
            equipmentModelRepository.deleteEquipmentByEquipmentModelId(uuid);
        } catch (Exception e) {
            throw e;
        }
    }

    public void update(EquipmentModelRequest equipmentModelRequest) {
        if (equipmentModelRepository.findById(UUID.fromString(equipmentModelRequest.getId())).isEmpty())
            throw new NullPointerException();
        EquipmentModel equipmentModel = EquipmentModel.builder()
                .id(UUID.fromString(equipmentModelRequest.getId()))
                .name(equipmentModelRequest.getName())
                .build();

        equipmentModelRepository.save(equipmentModel);
    }

    public Optional<EquipmentModel> findEquipmentModelById(UUID uuid) {
        Optional<EquipmentModel> equipmentModel = equipmentModelRepository.findById(uuid);
        if (equipmentModel.isPresent()) return equipmentModel;
        throw new NullPointerException();
    }


}
