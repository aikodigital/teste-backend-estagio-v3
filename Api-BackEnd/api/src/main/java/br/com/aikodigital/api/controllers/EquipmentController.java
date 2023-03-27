package br.com.aikodigital.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aikodigital.api.models.Equipment;
import br.com.aikodigital.api.repositorys.EquipmentRepository;


@RestController
@RequestMapping("/equipments")
public class EquipmentController {
    private EquipmentRepository repository;

    public EquipmentController(EquipmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Iterable<Equipment> getAllEquipment(){
        return repository.findAll();
    }

    @PostMapping("/create")
    public Equipment create(@RequestBody Equipment obj){
        return repository.save(obj);
    }

    @PutMapping("/update")
    public ResponseEntity<Equipment> update(@RequestBody Equipment obj){
        return  repository.findById(obj.getId()).map(recordFound -> {
            recordFound.setName(obj.getName());

            Equipment updated = repository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/delete")
    public void delete(@RequestBody Equipment obj){
        repository.delete(obj);
    }


}
