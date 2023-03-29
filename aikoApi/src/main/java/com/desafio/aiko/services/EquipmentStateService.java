package com.desafio.aiko.services;

import com.desafio.aiko.models.entities.EquipmentState;
import com.desafio.aiko.models.request.EquipmentStateRequest;
import com.desafio.aiko.repositories.EquipmentStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquipmentStateService {
    @Autowired
    EquipmentStateRepository equipmentStateRepository;

    public EquipmentStateRequest toDto(EquipmentState equipmentState) {
        return new EquipmentStateRequest(UUID.randomUUID().toString(), equipmentState.getName(), equipmentState.getColor());
    }


    public void create(EquipmentStateRequest equipmentStateRequest) {
        try {
            EquipmentState equipmentState = EquipmentState.builder()
                    .id(UUID.randomUUID())
                    .name(equipmentStateRequest.getName())
                    .color(equipmentStateRequest.getColor())
                    .build();

            equipmentStateRepository.save(equipmentState);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EquipmentStateRequest> findAllStates() {
        List<EquipmentState> equipmentStateList = equipmentStateRepository.findAll();
        List<EquipmentStateRequest> equipmentRequests = equipmentStateList.stream().map(this::toDto).collect(Collectors.toList());

        if (equipmentStateList.isEmpty()) throw new NullPointerException();
        return equipmentRequests;
    }

    public void deleteById(UUID uuid) {
        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(uuid);
        if (equipmentState.isPresent()) equipmentStateRepository.deleteById(uuid);
        throw new NullPointerException();
    }

    public void update(EquipmentStateRequest equipmentStateRequest) {
        try {
            EquipmentState equipmentState = EquipmentState.builder()
                    .id(UUID.fromString(equipmentStateRequest.getId()))
                    .name(equipmentStateRequest.getName())
                    .color(equipmentStateRequest.getColor())
                    .build();

            equipmentStateRepository.save(equipmentState);
        }catch (Exception e){
            throw e;
        }
    }
}
