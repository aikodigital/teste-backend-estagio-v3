package com.aiko.martins.matheus.equipments.repository;

import com.aiko.martins.matheus.equipments.domain.Equipment_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Equipment_modelRepository extends JpaRepository<Equipment_model, Integer> {

}
