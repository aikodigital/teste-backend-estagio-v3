package com.aiko.martins.matheus.equipments.repository;

import com.aiko.martins.matheus.equipments.domain.Equipment_state_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Equipment_state_historyRepository extends JpaRepository<Equipment_state_history, Integer> {

    public List<Equipment_state_history> findByOrderByDateDesc();

}
