package me.dri.aiko.repositories;


import me.dri.aiko.entities.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentModelsRepository extends JpaRepository<EquipmentModel, UUID> {

    @Query("SELECT e FROM EquipmentModel e WHERE e.name = :name")
    Optional<EquipmentModel> findByName(@Param("name") String name);

}
