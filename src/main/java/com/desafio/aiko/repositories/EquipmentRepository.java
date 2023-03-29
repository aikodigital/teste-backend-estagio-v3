package com.desafio.aiko.repositories;

import com.desafio.aiko.models.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@EnableTransactionManagement
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    @Query(value = "select * from operation.equipment", nativeQuery = true)
    List<Equipment> findAllEquipments();

    @Transactional
    @Modifying
    @Query(value =
            "DELETE FROM operation.equipment_position_history WHERE equipment_id IN (SELECT id FROM operation.equipment WHERE id = ?1); " +
                    "DELETE FROM operation.equipment_state_history WHERE equipment_id IN (SELECT id FROM operation.equipment WHERE id = ?1); " +
                    "DELETE FROM operation.equipment WHERE id = ?1", nativeQuery = true)
    void deleteEquipmentAndRelatedEntities(UUID id);



}
