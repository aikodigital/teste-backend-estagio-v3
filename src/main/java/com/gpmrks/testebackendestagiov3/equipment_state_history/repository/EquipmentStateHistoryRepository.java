package com.gpmrks.testebackendestagiov3.equipment_state_history.repository;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity.EquipmentModelStateHourlyEarnings;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentStateHistoryRepository extends JpaRepository<EquipmentStateHistory, EquipmentStateHistoryId> {

    @Query("FROM EquipmentStateHistory e WHERE e.equipment.id = :equipId")
    List<EquipmentStateHistory> getEquipmentStateHistoriesByEquipId(UUID equipId);

    @Query("FROM EquipmentStateHistory e WHERE e.equipmentState.id = :stateId")
    List<EquipmentStateHistory> getEquipmentStateHistoriesByStateId(UUID stateId);

    @Query("FROM EquipmentStateHistory e WHERE e.equipment.id = :equipId AND e.equipmentState.id = :stateId")
    List<EquipmentStateHistory> getEquipmentStateHistoriesByEquipAndStateIds(UUID equipId, UUID stateId);

    @Query("FROM EquipmentStateHistory e WHERE e.equipment.id = :equipId AND e.equipmentState.id = :stateId AND e.date = :date")
    EquipmentStateHistory getEquipmentStateHistoryByEquipmentAndStateIdsAndSpecificDateAndTime(UUID equipId, UUID stateId, LocalDateTime date);

    @Query("FROM EquipmentStateHistory e WHERE e.date >= :startDate AND e.date <= :endDate")
    List<EquipmentStateHistory> getEquipmentStateHistoriesByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
