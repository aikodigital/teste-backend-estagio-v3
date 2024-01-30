package me.dri.aiko.repositories;

import me.dri.aiko.entities.EquipmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentStateRepository  extends JpaRepository<EquipmentState, UUID> {


    @Query("SELECT e FROM EquipmentState e WHERE e.name = :name")
    Optional<EquipmentState> findEquipmentStateByName(@Param("name") String name);
    @Query("SELECT e FROM EquipmentState e WHERE e.id = :id")
    Optional<EquipmentState> findEquipmentStateById(@Param("id") UUID id);
}
