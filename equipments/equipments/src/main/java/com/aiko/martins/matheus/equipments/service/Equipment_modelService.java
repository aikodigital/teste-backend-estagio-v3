package com.aiko.martins.matheus.equipments.service;

import com.aiko.martins.matheus.equipments.domain.Equipment_model;
import com.aiko.martins.matheus.equipments.repository.Equipment_modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Equipment_modelService {

    @Autowired
    private Equipment_modelRepository repo;

    public List<Equipment_model> listAll(){
        return repo.findAll();
    }

    public void save(Equipment_model equipment) {
        repo.save(equipment);
    }

    public Equipment_model get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
