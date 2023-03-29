package com.aiko.martins.matheus.equipments.repository;

import com.aiko.martins.matheus.equipments.domain.Equipment_model_state_hourly_earnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Equipment_model_state_hourly_earningRepository extends JpaRepository<Equipment_model_state_hourly_earnings, Integer> {

}
