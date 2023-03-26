package com.gpmrks.testebackendestagiov3.equipment_position_history.service.impl;

import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import com.gpmrks.testebackendestagiov3.equipment_position_history.repository.EquipmentPositionHistoryRepository;
import com.gpmrks.testebackendestagiov3.equipment_position_history.service.EquipmentPositionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentPositionHistoryServiceImpl implements EquipmentPositionHistoryService {

    private EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;

    @Autowired
    public EquipmentPositionHistoryServiceImpl(EquipmentPositionHistoryRepository equipmentPositionHistoryRepository) {
        this.equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
    }

    @Override
    public List<EquipmentPositionHistory> getAllEquipmentsPositionHistories() {
        return equipmentPositionHistoryRepository.findAll();
    }
}
