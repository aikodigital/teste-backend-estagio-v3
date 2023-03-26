package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.service;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsForm;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;

import java.util.List;
import java.util.UUID;

public interface EquipmentModelStateHourlyEarningsService {

    List<EquipmentModelStateHourlyEarnings> getAllEquipmentsModelStateHourlyEarnings();

    List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByModelId(UUID modelId);

    List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByStateId(UUID stateId);

    EquipmentModelStateHourlyEarnings getEquipmentModelStateHourlyEarningsByModelAndStateIds(UUID modelId, UUID stateId);

    EquipmentModelStateHourlyEarnings createEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value);

    EquipmentModelStateHourlyEarnings updateEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsForm value);

    void deleteEquipmentModelStateHourlyEarnings(UUID modelId, UUID stateId);
}
