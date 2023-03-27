package br.com.aikodigital.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aikodigital.api.models.Equipment_State;
import br.com.aikodigital.api.repositorys.Equipment_State_Repository;

@RestController
@RequestMapping("/equipments_state")
public class Equipment_State_Controller {
    private Equipment_State_Repository repository;

    public Equipment_State_Controller(Equipment_State_Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Iterable<Equipment_State> getAllEquipmentStates() {
        return repository.findAll();
    }

    @PostMapping("/create")
    public Equipment_State create(@RequestBody Equipment_State obj){
        return repository.save(obj);
    }

    @PutMapping("/update")
    public ResponseEntity<Equipment_State> update(@RequestBody Equipment_State obj){
        return repository.findById(obj.getId()).map(recordFound -> {
            recordFound.setName(obj.getName());
            recordFound.setColor(obj.getColor());

            Equipment_State updated = repository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Equipment_State obj){
        repository.delete(obj);
    }
    
}
