package com.gpmrks.testebackendestagiov3.equipment_state_history.service.impl;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment.repository.EquipmentRepository;
import com.gpmrks.testebackendestagiov3.equipment.service.EquipmentService;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.EquipmentModelNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state.repository.EquipmentStateRepository;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
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
    private EquipmentRepository equipmentRepository;
    private EquipmentStateRepository equipmentStateRepository;
    private EquipmentService equipmentService;
    private EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentStateHistoryServiceImpl(EquipmentStateHistoryRepository equipmentStateHistoryRepository, EquipmentRepository equipmentRepository, EquipmentStateRepository equipmentStateRepository, EquipmentService equipmentService, EquipmentStateService equipmentStateService) {
        this.equipmentStateHistoryRepository = equipmentStateHistoryRepository;
        this.equipmentRepository = equipmentRepository;
        this.equipmentStateRepository = equipmentStateRepository;
        this.equipmentService = equipmentService;
        this.equipmentStateService = equipmentStateService;
    }

    @Override
    public List<EquipmentStateHistoryDTO> getAllEquipmentsStateHistories() {
        List<EquipmentStateHistory> equipmentStateHistoryList = equipmentStateHistoryRepository.findAll();
        return equipmentStateHistoryList.stream().map(EquipmentStateHistoryDTO::new).toList();
    }

    @Override
    public List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByEquipId(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        List<EquipmentStateHistory> equipmentStateHistoriesByEquipIdList = equipmentStateHistoryRepository.getEquipmentStateHistoriesByEquipId(equipmentId);
        return equipmentStateHistoriesByEquipIdList.stream().map(EquipmentStateHistoryDTO::new).toList();
    }

    @Override
    public List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByStateId(UUID stateId) {
        equipmentStateService.getEquipmentStateById(stateId);
        List<EquipmentStateHistory> equipmentStateHistoriesByStateIdList = equipmentStateHistoryRepository.getEquipmentStateHistoriesByStateId(stateId);
        return equipmentStateHistoriesByStateIdList.stream().map(EquipmentStateHistoryDTO::new).toList();
    }

    @Override
    public List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByEquipAndStateIds(UUID equipmentId, UUID stateId) {
        equipmentService.getEquipmentById(equipmentId);
        equipmentStateService.getEquipmentStateById(stateId);
        List<EquipmentStateHistory> equipmentStateHistoriesByEquipAndStateIdsList = equipmentStateHistoryRepository.getEquipmentStateHistoriesByEquipAndStateIds(equipmentId, stateId);
        return equipmentStateHistoriesByEquipAndStateIdsList.stream().map(EquipmentStateHistoryDTO::new).toList();
    }

    @Override
    public List<EquipmentStateHistoryDTO> getEquipmentStateHistoryByDate(EquipmentHistoryDateForm dateRange) {
        List<EquipmentStateHistory> equipmentStateHistoriesByDateList = equipmentStateHistoryRepository.getEquipmentStateHistoriesByDate(dateRange.getStartDate(), dateRange.getEndDate());
        return equipmentStateHistoriesByDateList.stream().map(EquipmentStateHistoryDTO::new).toList();
    }

    @Override
    public EquipmentStateHistoryDTO registerEquipmentState(UUID equipmentId, UUID stateId) {
        EquipmentStateHistory equipmentStateHistory = new EquipmentStateHistory();
        Equipment equipment = returnEquipmentIfExistsOrElseThrowEquipmentNotFound(equipmentId);
        EquipmentState equipmentState = returnEquipmentStateIfExistsOrElseThrowStateNotFound(stateId);

        equipmentStateHistory.setEquipment(equipment);
        equipmentStateHistory.setEquipmentState(equipmentState);

        EquipmentStateHistory equipmentStateHistoryRegistered = equipmentStateHistoryRepository.save(equipmentStateHistory);

        return new EquipmentStateHistoryDTO(equipmentStateHistoryRegistered);
    }

    @Override
    public void deleteEquipmentStateHistory(UUID equipmentId) {
        equipmentService.getEquipmentById(equipmentId);
        List<EquipmentStateHistory> equipmentStateHistoriesByEquipAndStateIds = equipmentStateHistoryRepository.getEquipmentStateHistoriesByEquipId(equipmentId);
        equipmentStateHistoryRepository.deleteAll(equipmentStateHistoriesByEquipAndStateIds);
    }

    private Equipment returnEquipmentIfExistsOrElseThrowEquipmentNotFound(UUID id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new EquipmentModelNotFoundException(id));
    }

    private EquipmentState returnEquipmentStateIfExistsOrElseThrowStateNotFound(UUID stateId) {
        return equipmentStateRepository.findById(stateId).orElseThrow(() -> new EquipmentModelNotFoundException(stateId));
    }
}
