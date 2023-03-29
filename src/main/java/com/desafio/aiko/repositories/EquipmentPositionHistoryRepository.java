package com.desafio.aiko.repositories;

import com.desafio.aiko.models.entities.EquipmentPositionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@EnableTransactionManagement
@Repository
public interface EquipmentPositionHistoryRepository extends JpaRepository<EquipmentPositionHistory, UUID> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO operation.equipment_position_history (equipment_id, \"date\", lat, lon) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
    void insert(@Param("uuid") UUID uuid, @Param("date") Timestamp date, @Param("lat") Double lat, @Param("lon") Double lon);

    @Query(value = "select * from operation.equipment_position_history", nativeQuery = true)
    List<EquipmentPositionHistory> findAllPositions();

    @Modifying
    @Transactional
    @Query(value = "update operation.equipment_position_history set equipment_id=?1, lat=?2, lon=?3 where date=?4", nativeQuery = true)
    void updatePosition(UUID uuid, Double lat, Double lon, Timestamp date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation.equipment_position_history WHERE equipment_id IN (SELECT id FROM operation.equipment WHERE id = ?1);", nativeQuery = true)
    void deletePositions(UUID uuid);
}
