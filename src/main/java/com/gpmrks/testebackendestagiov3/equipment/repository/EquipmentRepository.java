package com.gpmrks.testebackendestagiov3.equipment.repository;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    @Query("FROM Equipment e WHERE e.equipmentModel.id = :equipModelId")
    List<Equipment> getEquipmentsByModelId(UUID equipModelId);
}