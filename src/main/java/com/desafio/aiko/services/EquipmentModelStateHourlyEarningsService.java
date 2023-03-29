package com.desafio.aiko.services;

import com.desafio.aiko.models.entities.EquipmentModelStateHourlyEarnings;
import com.desafio.aiko.models.id.EquipmentModelStateHourlyEarningsId;
import com.desafio.aiko.models.request.EquipmentModelStateHourlyEarningsRequest;
import com.desafio.aiko.repositories.EquipmentModelStateHourlyEarningsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EquipmentModelStateHourlyEarningsService {
    @Autowired
    EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningsRepository;


    public EquipmentModelStateHourlyEarningsRequest toDto(EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings) {
        EquipmentModelStateHourlyEarningsId equipmentModelStateHourlyEarningsId = EquipmentModelStateHourlyEarningsId.builder()
                .equipmentModelId(equipmentModelStateHourlyEarnings.getId().getEquipmentModelId())
                .equipmentStateId(equipmentModelStateHourlyEarnings.getId().getEquipmentStateId())
                .build();
        return new EquipmentModelStateHourlyEarningsRequest(equipmentModelStateHourlyEarningsId.getEquipmentStateId().toString(), equipmentModelStateHourlyEarningsId.getEquipmentModelId().toString(), equipmentModelStateHourlyEarnings.getValue());
    }


    public void create(EquipmentModelStateHourlyEarningsRequest equipmentModelStateHourlyEarningsRequest) {
        try {


            EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = EquipmentModelStateHourlyEarnings.builder()
                    .value(equipmentModelStateHourlyEarningsRequest.getValue())
                    .id(EquipmentModelStateHourlyEarningsId.builder()
                            .equipmentModelId(UUID.fromString(equipmentModelStateHourlyEarningsRequest.getEquipmentModelId()))
                            .equipmentStateId(UUID.fromString(equipmentModelStateHourlyEarningsRequest.getEquipmentStateId()))
                            .build())
                    .build();


            equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EquipmentModelStateHourlyEarningsRequest> equipmentModelStateHourlyEarningsList() {
        List<EquipmentModelStateHourlyEarnings> equipmentEarningsList = equipmentModelStateHourlyEarningsRepository.findAllEarnings();
        List<EquipmentModelStateHourlyEarningsRequest> equipmentModelStateHourlyEarningsRequestsList = equipmentEarningsList.stream().map(this::toDto).toList();
        if (equipmentModelStateHourlyEarningsRequestsList.isEmpty()) throw new NullPointerException();

        return equipmentModelStateHourlyEarningsRequestsList;

    }

    public void delete(EquipmentModelStateHourlyEarningsRequest equipmentModelStateHourlyEarningsRequest) {
        try {
            EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = EquipmentModelStateHourlyEarnings.builder()
                    .id(EquipmentModelStateHourlyEarningsId.builder()
                            .equipmentModelId(UUID.fromString(equipmentModelStateHourlyEarningsRequest.getEquipmentModelId()))
                            .equipmentStateId(UUID.fromString(equipmentModelStateHourlyEarningsRequest.getEquipmentStateId()))
                            .build())
                    .value(equipmentModelStateHourlyEarningsRequest.getValue())
                    .build();

            equipmentModelStateHourlyEarningsRepository.delete(equipmentModelStateHourlyEarnings);
        } catch (Exception e) {
            throw e;
        }
    }

    public void update(EquipmentModelStateHourlyEarningsRequest equipmentModelStateHourlyEarningsRequest) {
        try {
            EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = EquipmentModelStateHourlyEarnings.builder()
                    .id(EquipmentModelStateHourlyEarningsId.builder()
                            .equipmentModelId(UUID.fromString(equipmentModelStateHourlyEarningsRequest.getEquipmentModelId()))
                            .equipmentStateId(UUID.fromString(equipmentModelStateHourlyEarningsRequest.getEquipmentStateId()))
                            .build())
                    .value(equipmentModelStateHourlyEarningsRequest.getValue())
                    .build();

            equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        } catch (Exception e) {
            throw e;
        }
    }


}
