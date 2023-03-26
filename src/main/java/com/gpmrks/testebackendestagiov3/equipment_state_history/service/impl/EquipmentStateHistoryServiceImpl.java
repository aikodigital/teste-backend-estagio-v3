package com.gpmrks.testebackendestagiov3.equipment_state_history.service.impl;

import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistoryId;
import com.gpmrks.testebackendestagiov3.equipment_state_history.repository.EquipmentStateHistoryRepository;
import com.gpmrks.testebackendestagiov3.equipment_state_history.service.EquipmentStateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentStateHistoryServiceImpl implements EquipmentStateHistoryService {

    private EquipmentStateHistoryRepository equipmentStateHistoryRepository;

    @Autowired
    public EquipmentStateHistoryServiceImpl(EquipmentStateHistoryRepository equipmentStateHistoryRepository) {
        this.equipmentStateHistoryRepository = equipmentStateHistoryRepository;
    }

    @Override
    public List<EquipmentStateHistory> getAllEquipmentsStateHistories() {
        return equipmentStateHistoryRepository.findAll();
    }

    @Override
    public Optional<EquipmentStateHistory> getEquipmentStateHistoryById(EquipmentStateHistoryId id) {
        return equipmentStateHistoryRepository.findById(id);
    }
}
