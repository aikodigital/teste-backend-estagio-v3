package com.gpmrks.testebackendestagiov3.equipment_position_history.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment.repository.EquipmentRepository;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionForm;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import com.gpmrks.testebackendestagiov3.equipment_position_history.exception.InvalidEquipmentPositionException;
import com.gpmrks.testebackendestagiov3.equipment_position_history.repository.EquipmentPositionHistoryRepository;
import com.gpmrks.testebackendestagiov3.equipment_position_history.service.EquipmentPositionHistoryService;
import com.gpmrks.testebackendestagiov3.util.EquipmentHistoryDateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentPositionHistoryServiceImpl implements EquipmentPositionHistoryService {

    private EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;

    private EquipmentRepository equipmentRepository;

    private EquipmentService equipmentService;


    @Autowired
    public EquipmentPositionHistoryServiceImpl(EquipmentPositionHistoryRepository equipmentPositionHistoryRepository, EquipmentRepository equipmentRepository, EquipmentService equipmentService) {
        this.equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
        this.equipmentRepository = equipmentRepository;
        this.equipmentService = equipmentService;
    }

    @Autowired


    @Override
    public List<EquipmentPositionHistoryDTO> getAllEquipmentsPositionHistories() {
        List<EquipmentPositionHistory> equipmentPositionHistoryList = equipmentPositionHistoryRepository.findAll();
        return equipmentPositionHistoryList.stream().map(EquipmentPositionHistoryDTO::new).toList();
    }

    @Override
    public List<EquipmentPositionHistoryDTO> getEquipmentPositionHistoryByEquipId(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        List<EquipmentPositionHistory> equipmentPositionHistoryByEquipIdList = equipmentPositionHistoryRepository.getEquipmentPositionHistoryByEquipId(equipmentId);
        return equipmentPositionHistoryByEquipIdList.stream().map(EquipmentPositionHistoryDTO::new).toList();
    }

    @Override
    public List<EquipmentPositionHistoryDTO> getEquipmentPositionHistoryByDate(EquipmentHistoryDateForm equipmentHistoryDateForm) {
        List<EquipmentPositionHistory> equipmentPositionHistoriesByDateRange = equipmentPositionHistoryRepository.getEquipmentPositionHistoriesByDateRange(equipmentHistoryDateForm.getStartDate(), equipmentHistoryDateForm.getEndDate());
        return equipmentPositionHistoriesByDateRange.stream().map(EquipmentPositionHistoryDTO::new).toList();
    }

    @Override
    public EquipmentPositionHistoryDTO registerEquipmentPosition(UUID equipmentId, EquipmentPositionForm equipmentPositionToCreate) {
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(() -> new EquipmentNotFoundException(equipmentId));

        validatePosition(equipmentPositionToCreate);

        EquipmentPositionHistory equipmentPositionHistory = new EquipmentPositionHistory();

        equipmentPositionHistory.setEquipment(equipment);
        equipmentPositionHistory.setLat(equipmentPositionToCreate.getLat());
        equipmentPositionHistory.setLon(equipmentPositionToCreate.getLon());

        EquipmentPositionHistory equipmentPositionHistorySaved = equipmentPositionHistoryRepository.save(equipmentPositionHistory);

        return new EquipmentPositionHistoryDTO(equipmentPositionHistorySaved);
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
