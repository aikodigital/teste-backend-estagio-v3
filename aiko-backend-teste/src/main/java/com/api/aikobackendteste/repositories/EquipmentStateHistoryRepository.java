package com.api.aikobackendteste.repositories;

import com.api.aikobackendteste.models.EquipmentStateHistoryModel;
import com.api.aikobackendteste.primaryKeys.EquipmentPositionHistoryPk;
import com.api.aikobackendteste.primaryKeys.EquipmentStateHistoryPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentStateHistoryRepository extends JpaRepository<EquipmentStateHistoryModel, EquipmentStateHistoryPk> {

    Optional<EquipmentStateHistoryModel> findById(EquipmentPositionHistoryPk equipmentId);
}
