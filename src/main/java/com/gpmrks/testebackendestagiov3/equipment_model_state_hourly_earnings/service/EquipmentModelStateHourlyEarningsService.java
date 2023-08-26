package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EquipmentModelStateHourlyEarningsService {

    @Transactional(readOnly = true)
    List<EquipmentModelStateHourlyEarningsDTO> getAllEquipmentsModelStateHourlyEarnings();

    @Transactional(readOnly = true)
    List<EquipmentModelStateHourlyEarningsDTO> getEquipmentModelStateHourlyEarningsByModelId(UUID modelId);

    @Transactional(readOnly = true)
    List<EquipmentModelStateHourlyEarningsDTO> getEquipmentModelStateHourlyEarningsByStateId(UUID stateId);

    @Transactional(readOnly = true)
    EquipmentModelStateHourlyEarningsDTO getEquipmentModelStateHourlyEarningsByModelAndStateIds(UUID modelId, UUID stateId);

    @Transactional
    EquipmentModelStateHourlyEarningsDTO createEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value);

    @Transactional
    EquipmentModelStateHourlyEarningsDTO updateEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value);

    @Transactional
    void deleteEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId);
}
