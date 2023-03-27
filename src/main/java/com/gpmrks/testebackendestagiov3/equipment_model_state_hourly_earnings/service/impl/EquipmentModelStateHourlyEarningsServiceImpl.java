package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service.impl;

import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.repository.EquipmentModelStateHourlyEarningsRepository;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service.EquipmentModelStateHourlyEarningsService;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentModelStateHourlyEarningsServiceImpl implements EquipmentModelStateHourlyEarningsService {

    private EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningsRepository;
    private EquipmentModelService equipmentModelService;
    private EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentModelStateHourlyEarningsServiceImpl(EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningsRepository, EquipmentModelService equipmentModelService, EquipmentStateService equipmentStateService) {
        this.equipmentModelStateHourlyEarningsRepository = equipmentModelStateHourlyEarningsRepository;
        this.equipmentModelService = equipmentModelService;
        this.equipmentStateService = equipmentStateService;
    }

    @Override
    public List<EquipmentModelStateHourlyEarnings> getAllEquipmentsModelStateHourlyEarnings() {
        return equipmentModelStateHourlyEarningsRepository.findAll();
    }

    @Override
    public List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByModelId(UUID modelId) {
        equipmentModelService.getEquipModelById(modelId);
        return equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelId(modelId);
    }

    @Override
    public List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByStateId(UUID stateId) {
        equipmentStateService.getEquipmentStateById(stateId);
        return equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByStateId(stateId);
    }

    @Override
    public EquipmentModelStateHourlyEarnings getEquipmentModelStateHourlyEarningsByModelAndStateIds(UUID modelId, UUID stateId) {
        equipmentModelService.getEquipModelById(modelId);
        equipmentStateService.getEquipmentStateById(stateId);
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsByModelAndStateIds = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        return equipmentModelStateHourlyEarningsByModelAndStateIds;
    }

    @Override
    public EquipmentModelStateHourlyEarnings createEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarnings();
        equipmentModelStateHourlyEarnings.setEquipmentModel(equipmentModelService.getEquipModelById(modelId));
        equipmentModelStateHourlyEarnings.setEquipmentState(equipmentStateService.getEquipmentStateById(stateId));
        equipmentModelStateHourlyEarnings.setValue(value.getValue());
        equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        return equipmentModelStateHourlyEarnings;
    }

    @Override
    public EquipmentModelStateHourlyEarnings updateEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value) {
        equipmentModelService.getEquipModelById(modelId);
        equipmentStateService.getEquipmentStateById(stateId);
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        equipmentModelStateHourlyEarnings.setValue(value.getValue());
        equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        return equipmentModelStateHourlyEarnings;
    }

    @Override
    public void deleteEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsByModelAndStateIds = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        equipmentModelStateHourlyEarningsRepository.delete(equipmentModelStateHourlyEarningsByModelAndStateIds);
    }
}
