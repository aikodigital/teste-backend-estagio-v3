package com.desafio.aiko.repositories;

import com.desafio.aiko.models.entities.EquipmentStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public interface EquipmentStateHistoryRepository extends JpaRepository<EquipmentStateHistory, UUID> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO operation.equipment_state_history (equipment_id, \"date\", equipment_state_id) VALUES(?1, ?2, ?3)", nativeQuery = true)
    void insert(@Param("equipment_id") UUID equipmentId, @Param("date") Timestamp date, @Param("equipment_state_id") UUID equipmentStateId);

    @Modifying
    @Transactional
    @Query(value = "update operation.equipment_state_history set equipment_id=?1,equipment_state_id=?2 where date=?3", nativeQuery = true)
    void updateState(UUID equipmentId ,UUID equipmentStateId, Timestamp date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation.equipment_state_history WHERE equipment_id IN (SELECT id FROM operation.equipment WHERE id = ?1);", nativeQuery = true)
    void deleteStateHistories(UUID uuid);
}
