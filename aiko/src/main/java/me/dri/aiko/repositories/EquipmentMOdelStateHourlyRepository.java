package me.dri.aiko.repositories;

import me.dri.aiko.entities.EquipmentModelStateHourlyEarnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentMOdelStateHourlyRepository  extends JpaRepository<EquipmentModelStateHourlyEarnings, Long> {


    @Query("SELECT e FROM EquipmentModelStateHourlyEarnings e WHERE e.id.equipmentModel.id  = :id")
    Optional<EquipmentModelStateHourlyEarnings> findEquipmentModelStateHourlyEarningsByEquipmentModelName(@Param("id") UUID id);
}
