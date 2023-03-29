package com.aiko.martins.matheus.equipments.service;

import com.aiko.martins.matheus.equipments.domain.Equipment;
import com.aiko.martins.matheus.equipments.repository.EquipmentRepository;
import com.aiko.martins.matheus.equipments.repository.Equipment_state_historyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repo;

    @Autowired
    private Equipment_state_historyRepository repo1;

    public List<Equipment> listAll(){
        return repo.findAll();
    }

    public void save(Equipment equipment) {
        repo.save(equipment);
    }

    public Equipment get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
