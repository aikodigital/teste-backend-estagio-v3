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
import br.com.aikodigital.api.models.Equipment_State_History;
import br.com.aikodigital.api.repositorys.Equipment_State_History_Repository;

@RestController
@RequestMapping("/equipments_state_history")
public class Equipment_State_History_Controller {
    private Equipment_State_History_Repository repository;

    public Equipment_State_History_Controller(Equipment_State_History_Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Iterable<Equipment_State_History> getAllStateHistory(){
        return repository.findAll();
    }

    @PostMapping("/create")
     public Equipment_State_History create(@RequestBody Equipment_State_History obj){
        return repository.save(obj);
        
    }

    @PutMapping("/update")
    public ResponseEntity<Equipment_State_History> update(@RequestBody Equipment_State_History obj){
        return repository.findById(obj.getId()).map(recordFound -> {
            recordFound.setDate(obj.getDate());

            Equipment_State_History updated = repository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Equipment_State_History obj){
        repository.delete(obj);
    }
}
