package com.api.aikobackendteste.repositories;

import com.api.aikobackendteste.models.EquipmentPositionHistoryModel;
import com.api.aikobackendteste.primaryKeys.EquipmentPositionHistoryPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EquipmentPositionHistoryRepository extends JpaRepository<EquipmentPositionHistoryModel, EquipmentPositionHistoryPk> {

    Optional<EquipmentPositionHistoryModel> findById(EquipmentPositionHistoryPk equipmentId);

}
