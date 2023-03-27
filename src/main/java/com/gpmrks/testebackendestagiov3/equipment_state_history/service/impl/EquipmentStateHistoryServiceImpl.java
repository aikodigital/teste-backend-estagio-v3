package com.gpmrks.testebackendestagiov3.equipment_state_history.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.repository.EquipmentStateHistoryRepository;
import com.gpmrks.testebackendestagiov3.equipment_state_history.service.EquipmentStateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentStateHistoryServiceImpl implements EquipmentStateHistoryService {

    private EquipmentStateHistoryRepository equipmentStateHistoryRepository;

    private EquipmentService equipmentService;

    private EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentStateHistoryServiceImpl(EquipmentStateHistoryRepository equipmentStateHistoryRepository, EquipmentService equipmentService, EquipmentStateService equipmentStateService) {
        this.equipmentStateHistoryRepository = equipmentStateHistoryRepository;
        this.equipmentService = equipmentService;
        this.equipmentStateService = equipmentStateService;
    }

    @Override
    public List<EquipmentStateHistory> getAllEquipmentsStateHistories() {
        return equipmentStateHistoryRepository.findAll();
    }

    @Override
    public List<EquipmentStateHistory> getEquipmentStateHistoryByEquipId(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        return equipmentStateHistoryRepository.getEquipmentStateHistoriesByEquipId(equipmentId);
    }

    @Override
    public List<EquipmentStateHistory> getEquipmentStateHistoryByStateId(UUID stateId) {
        equipmentStateService.getEquipmentStateById(stateId);
        return equipmentStateHistoryRepository.getEquipmentStateHistoriesByStateId(stateId);
    }

    @Override
    public List<EquipmentStateHistory> getEquipmentStateHistoryByEquipAndStateIds(UUID equipmentId, UUID stateId) {
        equipmentService.getEquipmentById(equipmentId);
        equipmentStateService.getEquipmentStateById(stateId);
        return equipmentStateHistoryRepository.getEquipmentStateHistoriesByEquipAndStateIds(equipmentId, stateId);
    }

    @Override
    public List<EquipmentStateHistory> getEquipmentStateHistoryByDate(EquipmentHistoryDateForm dateRange) {
        return equipmentStateHistoryRepository.getEquipmentStateHistoriesByDate(dateRange.getStartDate(), dateRange.getEndDate());
    }

    @Override
    public EquipmentStateHistory registerEquipmentState(UUID equipmentId, UUID stateId) {
        EquipmentStateHistory equipmentStateHistory = new EquipmentStateHistory();
        equipmentStateHistory.setEquipment(equipmentService.getEquipmentById(equipmentId));
        equipmentStateHistory.setEquipmentState(equipmentStateService.getEquipmentStateById(stateId));
        equipmentStateHistoryRepository.save(equipmentStateHistory);
        return equipmentStateHistory;
    }

    @Override
    public void deleteEquipmentStateHistory(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        List<EquipmentStateHistory> equipmentStateHistoriesByEquipAndStateIds = equipmentStateHistoryRepository.getEquipmentStateHistoriesByEquipId(equipmentId);
        equipmentStateHistoryRepository.deleteAll(equipmentStateHistoriesByEquipAndStateIds);
    }

}
