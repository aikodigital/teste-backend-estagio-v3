package com.aiko.martins.matheus.equipments.repository;

import com.aiko.martins.matheus.equipments.domain.Equipment_position_history;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Equipment_position_historyRepository extends JpaRepository<Equipment_position_history, Integer> {

    public List<Equipment_position_history> findByOrderByDateDesc();

}
