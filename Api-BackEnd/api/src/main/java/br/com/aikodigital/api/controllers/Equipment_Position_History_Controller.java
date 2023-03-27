package br.com.aikodigital.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aikodigital.api.models.Equipment_position_history;
import br.com.aikodigital.api.repositorys.Equipment_position_history_Repository;

@RestController
@RequestMapping("/equipments_pos_his")
public class Equipment_Position_History_Controller {
    private Equipment_position_history_Repository repository;

    public Equipment_Position_History_Controller(Equipment_position_history_Repository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/list")
    public Iterable<Equipment_position_history> getAllEquipmentStates() {
        return repository.findAll();
    }

    @PostMapping("/create")
    public Equipment_position_history create(@RequestBody Equipment_position_history obj){
        return repository.save(obj);
    }

    @PutMapping("/update")
    public ResponseEntity<Equipment_position_history> update(@RequestBody Equipment_position_history obj){
        return repository.findById(obj.getEquipment_id()).map(recordFound -> {
            recordFound.setDate(obj.getDate());
            recordFound.setLat(obj.getLat());
            recordFound.setLon(obj.getLon());

            Equipment_position_history updated = repository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Equipment_position_history obj){
        repository.delete(obj);
    }
    
}
