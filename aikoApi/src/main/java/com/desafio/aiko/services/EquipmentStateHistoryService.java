package com.desafio.aiko.services;

import com.desafio.aiko.models.entities.Equipment;
import com.desafio.aiko.models.entities.EquipmentPositionHistory;
import com.desafio.aiko.models.entities.EquipmentState;
import com.desafio.aiko.models.entities.EquipmentStateHistory;
import com.desafio.aiko.models.id.EquipmentStateHistoryId;
import com.desafio.aiko.models.request.EquipmentPositionHistoryRequest;
import com.desafio.aiko.models.request.EquipmentStateHistoryRequest;
import com.desafio.aiko.repositories.EquipmentStateHistoryRepository;
import com.desafio.aiko.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquipmentStateHistoryService {
    @Autowired
    EquipmentStateHistoryRepository equipmentStateHistoryRepository;

    public EquipmentStateHistoryRequest toDto(EquipmentStateHistory equipmentStateHistory) {

        EquipmentStateHistoryRequest equipmentPositionHistoryRequest = EquipmentStateHistoryRequest.builder()
                .equipmentId(equipmentStateHistory.getId().getEquipmentId())
                .date(equipmentStateHistory.getDate())
                .equipmentStateId(equipmentStateHistory.getId().getEquipmentStateId())
                .build();

        return equipmentPositionHistoryRequest;
    }

    public void create(UUID equipmentId, UUID equipmentStateId) {
        Timestamp timestamp = Timestamp.valueOf((LocalDateTime.now()));
        String formattedTimeStamp = DateUtils.formatTimeStamp(timestamp);

        EquipmentStateHistory equipmentStateHistory = EquipmentStateHistory.builder()
                .id(EquipmentStateHistoryId.builder()
                        .equipmentId(equipmentId)
                        .equipmentStateId(equipmentStateId)
                        .build())
                .date(Timestamp.valueOf(formattedTimeStamp))
                .build();

        equipmentStateHistoryRepository.insert(equipmentStateHistory.getId().getEquipmentId(), equipmentStateHistory.getDate(), equipmentStateHistory.getId().getEquipmentStateId());
    }
    public List<EquipmentStateHistoryRequest> findLastState() {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryRepository.findAll(Sort.by("date").descending());
        List<EquipmentStateHistoryRequest> equipmentStateHistoryRequests = equipmentStateHistories.stream().map(this::toDto).collect(Collectors.toList());


        return equipmentStateHistoryRequests;
    }

    public void update(EquipmentStateHistoryRequest equipmentStateHistoryRequest){
        String formattedDate = DateUtils.formatTimeStamp(equipmentStateHistoryRequest.getDate());

        EquipmentStateHistory stateHistory = EquipmentStateHistory.builder()
                .id(EquipmentStateHistoryId.builder()
                        .equipmentStateId(equipmentStateHistoryRequest.getEquipmentStateId())
                        .equipmentId(equipmentStateHistoryRequest.getEquipmentId())
                        .build())
                .date(Timestamp.valueOf(formattedDate))
                .build();

        equipmentStateHistoryRepository.updateState(stateHistory.getId().getEquipmentId(),stateHistory.getId().getEquipmentStateId(),stateHistory.getDate());
    }
    public void delete(UUID uuid){
        EquipmentStateHistory equipmentStateHistory = EquipmentStateHistory.builder()
                .id(EquipmentStateHistoryId.builder()
                        .equipmentId(uuid)
                        .build())
                .build();
        equipmentStateHistoryRepository.deleteStateHistories(equipmentStateHistory.getId().getEquipmentId());
    }
}
