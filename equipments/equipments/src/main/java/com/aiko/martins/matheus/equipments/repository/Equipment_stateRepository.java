package com.aiko.martins.matheus.equipments.repository;

import com.aiko.martins.matheus.equipments.domain.Equipment_state;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Equipment_stateRepository extends JpaRepository<Equipment_state, Integer> {

}
