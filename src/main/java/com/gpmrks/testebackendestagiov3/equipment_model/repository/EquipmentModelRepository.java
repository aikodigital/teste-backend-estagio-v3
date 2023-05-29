package com.gpmrks.testebackendestagiov3.equipment_model.repository;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentModelRepository extends JpaRepository<EquipmentModel, UUID> {
}
