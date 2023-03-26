package com.gpmrks.testebackendestagiov3.equipment_state.repository;

import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentStateRepository extends JpaRepository<EquipmentState, UUID> {
}
