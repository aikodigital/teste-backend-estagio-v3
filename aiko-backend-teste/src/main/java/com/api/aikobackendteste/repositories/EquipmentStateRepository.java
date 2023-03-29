package com.api.aikobackendteste.repositories;

import com.api.aikobackendteste.models.EquipmentStateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentStateRepository extends JpaRepository<EquipmentStateModel, UUID> {
}
