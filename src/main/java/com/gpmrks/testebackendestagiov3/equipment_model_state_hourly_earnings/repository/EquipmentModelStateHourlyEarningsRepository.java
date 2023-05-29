package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.repository;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarningsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentModelStateHourlyEarningsRepository extends JpaRepository<EquipmentModelStateHourlyEarnings, EquipmentModelStateHourlyEarningsId> {

    @Query("FROM EquipmentModelStateHourlyEarnings e WHERE e.equipmentModel.id = :equipModelId")
    List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByModelId(UUID equipModelId);
    @Query("FROM EquipmentModelStateHourlyEarnings e WHERE e.equipmentState.id = :equipStateId")
    List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByStateId(UUID equipStateId);
    @Query("FROM EquipmentModelStateHourlyEarnings e WHERE e.equipmentModel.id = :modelId AND e.equipmentState.id = :stateId")
    EquipmentModelStateHourlyEarnings getEquipmentModelStateHourlyEarningsByModelAndStateIds(UUID modelId, UUID stateId);
}
