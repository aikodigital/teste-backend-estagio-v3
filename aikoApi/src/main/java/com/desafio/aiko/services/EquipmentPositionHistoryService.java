package com.desafio.aiko.services;

import com.desafio.aiko.models.entities.EquipmentPositionHistory;
import com.desafio.aiko.models.id.EquipmentPositionHistoryId;
import com.desafio.aiko.models.request.EquipmentPositionHistoryRequest;
import com.desafio.aiko.repositories.EquipmentModelRepository;
import com.desafio.aiko.repositories.EquipmentPositionHistoryRepository;
import com.desafio.aiko.repositories.EquipmentRepository;
import com.desafio.aiko.utils.DateUtils;
import jakarta.persistence.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquipmentPositionHistoryService {
    @Autowired
    EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    EquipmentModelRepository equipmentModelRepository;

    public EquipmentPositionHistoryRequest toDto(EquipmentPositionHistory equipmentPositionHistory) {


        EquipmentPositionHistoryRequest equipmentPositionHistoryRequest = EquipmentPositionHistoryRequest.builder()
                .equipmentId(equipmentPositionHistory.getId().getEquipmentId())
                .date(equipmentPositionHistory.getDate())
                .lat(equipmentPositionHistory.getLat())
                .lon(equipmentPositionHistory.getLon())
                .build();

        return equipmentPositionHistoryRequest;
    }

    public void create(EquipmentPositionHistoryRequest equipmentPositionHistoryRequest) {
        try {
            Timestamp timestamp = Timestamp.valueOf((LocalDateTime.now()));
            String formattedTimeStamp = DateUtils.formatTimeStamp(timestamp);

            EquipmentPositionHistory positionHistory = EquipmentPositionHistory.builder()
                    .id(EquipmentPositionHistoryId.builder()
                            .equipmentId(equipmentPositionHistoryRequest.getEquipmentId())
                            .build())
                    .lat(equipmentPositionHistoryRequest.getLat())
                    .lon(equipmentPositionHistoryRequest.getLon())
                    .date(Timestamp.valueOf(formattedTimeStamp))
                    .build();

            equipmentPositionHistoryRepository.insert(equipmentPositionHistoryRequest.getEquipmentId(), Timestamp.valueOf(formattedTimeStamp), positionHistory.getLat(), positionHistory.getLon());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EquipmentPositionHistoryRequest> findLastPositions() {
        List<EquipmentPositionHistory> equipmentPositionHistory = equipmentPositionHistoryRepository.findAll(Sort.by("date").descending());
        List<EquipmentPositionHistoryRequest> equipmentPositionHistoryRequestList = equipmentPositionHistory.stream().map(this::toDto).collect(Collectors.toList());


        return equipmentPositionHistoryRequestList;
    }

    public void update(EquipmentPositionHistoryRequest equipmentPositionHistoryRequest) {

        String formattedDate = DateUtils.formatTimeStamp(equipmentPositionHistoryRequest.getDate());

        EquipmentPositionHistory positionHistory = EquipmentPositionHistory.builder()
                .id(EquipmentPositionHistoryId.builder()
                        .equipmentId(equipmentPositionHistoryRequest.getEquipmentId())
                        .build())
                .lat(equipmentPositionHistoryRequest.getLat())
                .lon(equipmentPositionHistoryRequest.getLon())
                .date(Timestamp.valueOf(formattedDate))
                .build();


        equipmentPositionHistoryRepository.updatePosition(positionHistory.getId().getEquipmentId(), positionHistory.getLat(), positionHistory.getLon(), positionHistory.getDate());
    }

    public void delete(UUID uuid) {
        EquipmentPositionHistoryId equipmentPositionHistoryId = EquipmentPositionHistoryId.builder()
                .equipmentId(uuid)
                .build();

        equipmentPositionHistoryRepository.deletePositions(equipmentPositionHistoryId.getEquipmentId());
    }

}
