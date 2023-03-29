package com.desafio.aiko.repositories;

import com.desafio.aiko.models.entities.EquipmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentStateRepository extends JpaRepository<EquipmentState, UUID> {
}
