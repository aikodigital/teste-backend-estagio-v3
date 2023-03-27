package com.gpmrks.testebackendestagiov3.equipment_position_history.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionForm;
import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import com.gpmrks.testebackendestagiov3.equipment_position_history.exception.InvalidEquipmentPositionException;
import com.gpmrks.testebackendestagiov3.equipment_position_history.repository.EquipmentPositionHistoryRepository;
import com.gpmrks.testebackendestagiov3.equipment_position_history.service.EquipmentPositionHistoryService;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentPositionHistoryServiceImpl implements EquipmentPositionHistoryService {

    private EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;
    private EquipmentService equipmentService;

    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentPositionHistoryServiceImpl(EquipmentPositionHistoryRepository equipmentPositionHistoryRepository, EquipmentService equipmentService, EquipmentModelService equipmentModelService) {
        this.equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
        this.equipmentService = equipmentService;
        this.equipmentModelService = equipmentModelService;
    }

    @Override
    public List<EquipmentPositionHistory> getAllEquipmentsPositionHistories() {
        return equipmentPositionHistoryRepository.findAll();
    }

    @Override
    public List<EquipmentPositionHistory> getEquipmentPositionHistoryByEquipId(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        return equipmentPositionHistoryRepository.getEquipmentPositionHistoryByEquipId(equipmentId);
    }

    @Override
    public List<EquipmentPositionHistory> getEquipmentPositionHistoryByDate(EquipmentHistoryDateForm equipmentHistoryDateForm) {
        return equipmentPositionHistoryRepository.getEquipmentPositionHistoriesByDateRange(equipmentHistoryDateForm.getStartDate(), equipmentHistoryDateForm.getEndDate());
    }

    @Override
    public EquipmentPositionHistory registerEquipmentPosition(UUID equipmentId, EquipmentPositionForm equipmentPositionToCreate) {
        Equipment equipment = equipmentService.getEquipmentById(equipmentId);
        validatePosition(equipmentPositionToCreate);
        EquipmentPositionHistory equipmentPositionHistory = new EquipmentPositionHistory();
        equipmentPositionHistory.setEquipment(equipment);
        equipmentPositionHistory.setLat(equipmentPositionToCreate.getLat());
        equipmentPositionHistory.setLon(equipmentPositionToCreate.getLon());
        equipmentPositionHistoryRepository.save(equipmentPositionHistory);
        return equipmentPositionHistory;
    }

    @Override
    public void deleteEquipmentPositionHistory(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        List<EquipmentPositionHistory> equipmentPositionHistories = equipmentPositionHistoryRepository.getEquipmentPositionHistoryByEquipId(equipmentId);
        equipmentPositionHistoryRepository.deleteAll(equipmentPositionHistories);
    }

    private void validatePosition(EquipmentPositionForm equipmentPosition) {

        if ((equipmentPosition.getLat() < -90 || equipmentPosition.getLat() > 90) || (equipmentPosition.getLon() < -180 || equipmentPosition.getLon() > 180)) {
            throw new InvalidEquipmentPositionException(equipmentPosition.getLat(), equipmentPosition.getLon());
        }
    }
}
