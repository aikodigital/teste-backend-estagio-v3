package com.gpmrks.testebackendestagiov3.equipment_position_history.repository;

import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentPositionHistoryRepository extends JpaRepository<EquipmentPositionHistory, EquipmentPositionHistoryId> {

    @Query("FROM EquipmentPositionHistory e WHERE e.equipment.id = :equipmentId")
    List<EquipmentPositionHistory> getEquipmentPositionHistoryByEquipId(UUID equipmentId);

    @Query("FROM EquipmentPositionHistory e WHERE e.date >= :startDate AND e.date <= :endDate")
    List<EquipmentPositionHistory> getEquipmentPositionHistoriesByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
