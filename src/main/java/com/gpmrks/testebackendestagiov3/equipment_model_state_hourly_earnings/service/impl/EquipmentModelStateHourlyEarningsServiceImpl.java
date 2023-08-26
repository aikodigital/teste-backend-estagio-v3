package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service.impl;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.EquipmentModelNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_model.repository.EquipmentModelRepository;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.repository.EquipmentModelStateHourlyEarningsRepository;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service.EquipmentModelStateHourlyEarningsService;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state.exception.EquipmentStateNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_state.repository.EquipmentStateRepository;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentModelStateHourlyEarningsServiceImpl implements EquipmentModelStateHourlyEarningsService {

    private EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningsRepository;
    private EquipmentModelRepository equipmentModelRepository;
    private EquipmentStateRepository equipmentStateRepository;
    private EquipmentModelService equipmentModelService;
    private EquipmentStateService equipmentStateService;

    @Autowired
    public EquipmentModelStateHourlyEarningsServiceImpl(EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningsRepository, EquipmentModelRepository equipmentModelRepository, EquipmentStateRepository equipmentStateRepository, EquipmentModelService equipmentModelService, EquipmentStateService equipmentStateService) {
        this.equipmentModelStateHourlyEarningsRepository = equipmentModelStateHourlyEarningsRepository;
        this.equipmentModelRepository = equipmentModelRepository;
        this.equipmentStateRepository = equipmentStateRepository;
        this.equipmentModelService = equipmentModelService;
        this.equipmentStateService = equipmentStateService;
    }

    @Override
    public List<EquipmentModelStateHourlyEarningsDTO> getAllEquipmentsModelStateHourlyEarnings() {
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsRepository.findAll();
        return equipmentModelStateHourlyEarnings.stream().map(EquipmentModelStateHourlyEarningsDTO::new).toList();
    }

    @Override
    public List<EquipmentModelStateHourlyEarningsDTO> getEquipmentModelStateHourlyEarningsByModelId(UUID modelId) {
        equipmentModelService.getEquipModelById(modelId);
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsByModelIdList = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelId(modelId);
        return equipmentModelStateHourlyEarningsByModelIdList.stream().map(EquipmentModelStateHourlyEarningsDTO::new).toList();
    }

    @Override
    public List<EquipmentModelStateHourlyEarningsDTO> getEquipmentModelStateHourlyEarningsByStateId(UUID stateId) {
        equipmentStateService.getEquipmentStateById(stateId);
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsByStateIdList = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByStateId(stateId);
        return equipmentModelStateHourlyEarningsByStateIdList.stream().map(EquipmentModelStateHourlyEarningsDTO::new).toList();
    }

    @Override
    public EquipmentModelStateHourlyEarningsDTO getEquipmentModelStateHourlyEarningsByModelAndStateIds(UUID modelId, UUID stateId) {
        equipmentModelService.getEquipModelById(modelId);
        equipmentStateService.getEquipmentStateById(stateId);
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsByModelAndStateIds = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        return new EquipmentModelStateHourlyEarningsDTO(equipmentModelStateHourlyEarningsByModelAndStateIds);
    }

    @Override
    public EquipmentModelStateHourlyEarningsDTO createEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value) {
        EquipmentModel equipmentModel = returnEquipmentModelIfExistsOrElseThrowModelNotFound(modelId);
        EquipmentState equipmentState = returnEquipmentStateIfExistsOrElseThrowStateNotFound(stateId);

        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarnings();
        equipmentModelStateHourlyEarnings.setEquipmentModel(equipmentModel);
        equipmentModelStateHourlyEarnings.setEquipmentState(equipmentState);
        equipmentModelStateHourlyEarnings.setValue(value.getValue());

        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsSaved = equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        return new EquipmentModelStateHourlyEarningsDTO(equipmentModelStateHourlyEarningsSaved);
    }

    @Override
    public EquipmentModelStateHourlyEarningsDTO updateEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value) {
        equipmentModelService.getEquipModelById(modelId);
        equipmentStateService.getEquipmentStateById(stateId);
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        equipmentModelStateHourlyEarnings.setValue(value.getValue());
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsUpdated = equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        return new EquipmentModelStateHourlyEarningsDTO(equipmentModelStateHourlyEarningsUpdated);
    }

    @Override
    public void deleteEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId) {
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsByModelAndStateIds = equipmentModelStateHourlyEarningsRepository.getEquipmentModelStateHourlyEarningsByModelAndStateIds(modelId, stateId);
        equipmentModelStateHourlyEarningsRepository.delete(equipmentModelStateHourlyEarningsByModelAndStateIds);
    }

    private EquipmentModel returnEquipmentModelIfExistsOrElseThrowModelNotFound(UUID modelId) {
        return equipmentModelRepository.findById(modelId).orElseThrow(() -> new EquipmentModelNotFoundException(modelId));
    }

    private EquipmentState returnEquipmentStateIfExistsOrElseThrowStateNotFound(UUID stateId) {
        return equipmentStateRepository.findById(stateId).orElseThrow(() -> new EquipmentStateNotFoundException(stateId));
    }
}