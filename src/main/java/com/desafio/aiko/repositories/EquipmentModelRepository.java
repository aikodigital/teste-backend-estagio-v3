package com.desafio.aiko.repositories;

import com.desafio.aiko.models.entities.Equipment;
import com.desafio.aiko.models.entities.EquipmentModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@EnableTransactionManagement
@Repository
public interface EquipmentModelRepository extends CrudRepository<EquipmentModel, UUID> {

    @Query(value = "select * from operation.equipment_model", nativeQuery = true)
    List<EquipmentModel> findAllEquipmentModels();


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation.equipment_position_history WHERE equipment_id IN (SELECT id FROM operation.equipment WHERE equipment_model_id = ?1)", nativeQuery = true)
    void deleteEquipmentPositionHistoryByEquipmentModelId(UUID uuid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation.equipment_state_history WHERE equipment_id IN (SELECT id FROM operation.equipment WHERE equipment_model_id = ?1)", nativeQuery = true)
    void deleteEquipmentStateHistoryByEquipmentModelId(UUID uuid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation.equipment WHERE equipment_model_id = ?1", nativeQuery = true)
    void deleteEquipmentByEquipmentModelId(UUID uuid);






}
