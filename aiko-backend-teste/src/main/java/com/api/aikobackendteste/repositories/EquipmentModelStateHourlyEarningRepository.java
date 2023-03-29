package com.api.aikobackendteste.repositories;

import com.api.aikobackendteste.models.EquipmentModelStateHourlyEarningModel;
import com.api.aikobackendteste.primaryKeys.EquipmentModelStateHourlyEarningPK;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface EquipmentModelStateHourlyEarningRepository extends JpaRepository<EquipmentModelStateHourlyEarningModel, EquipmentModelStateHourlyEarningPK> {
    Optional<EquipmentModelStateHourlyEarningModel> findById(EquipmentModelStateHourlyEarningPK equipmentId);
}

