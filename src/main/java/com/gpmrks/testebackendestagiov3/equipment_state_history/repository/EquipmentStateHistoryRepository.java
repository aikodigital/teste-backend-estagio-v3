package com.gpmrks.testebackendestagiov3.equipment_state_history.repository;

import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistory;
import com.gpmrks.testebackendestagiov3.equipment_state_history.entity.EquipmentStateHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentStateHistoryRepository extends JpaRepository<EquipmentStateHistory, EquipmentStateHistoryId> {
}
