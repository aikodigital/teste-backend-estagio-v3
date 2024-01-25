package me.dri.aiko.repositories;

import me.dri.aiko.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentRepository  extends JpaRepository<Equipment, UUID> {

    @Query("SELECT e FROM Equipment e WHERE e.name = :name")
    Optional<Equipment> findByName(@Param("name") String name);

}
