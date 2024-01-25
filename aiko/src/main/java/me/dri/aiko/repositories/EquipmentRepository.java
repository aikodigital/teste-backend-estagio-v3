package me.dri.aiko.repositories;

import me.dri.aiko.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentRepository  extends JpaRepository<Equipment, UUID> {

}
