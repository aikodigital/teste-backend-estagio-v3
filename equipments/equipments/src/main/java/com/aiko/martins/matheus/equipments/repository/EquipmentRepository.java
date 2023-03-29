package com.aiko.martins.matheus.equipments.repository;

import com.aiko.martins.matheus.equipments.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
