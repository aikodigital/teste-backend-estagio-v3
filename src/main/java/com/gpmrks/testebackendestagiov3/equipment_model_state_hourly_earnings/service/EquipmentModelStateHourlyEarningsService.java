package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;

import java.util.List;
import java.util.UUID;

public interface EquipmentModelStateHourlyEarningsService {

    List<EquipmentModelStateHourlyEarningsDTO> getAllEquipmentsModelStateHourlyEarnings();

    List<EquipmentModelStateHourlyEarningsDTO> getEquipmentModelStateHourlyEarningsByModelId(UUID modelId);

    List<EquipmentModelStateHourlyEarningsDTO> getEquipmentModelStateHourlyEarningsByStateId(UUID stateId);

    EquipmentModelStateHourlyEarningsDTO getEquipmentModelStateHourlyEarningsByModelAndStateIds(UUID modelId, UUID stateId);

    EquipmentModelStateHourlyEarningsDTO createEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value);

    EquipmentModelStateHourlyEarningsDTO updateEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value);

    void deleteEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId);
}
