package com.desafio.aiko.repositories;

import com.desafio.aiko.models.entities.EquipmentModelStateHourlyEarnings;
import com.desafio.aiko.models.request.EquipmentModelStateHourlyEarningsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentModelStateHourlyEarningsRepository extends JpaRepository<EquipmentModelStateHourlyEarnings, UUID> {

    @Query(value = "select * from operation.equipment_model_state_hourly_earnings", nativeQuery = true)
    List<EquipmentModelStateHourlyEarnings> findAllEarnings();
}
