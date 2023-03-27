package br.com.aikodigital.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aikodigital.api.models.Equipment_Model;
import br.com.aikodigital.api.repositorys.Equipment_ModelRepository;

@RestController
@RequestMapping("/equipments_model")
public class Equipment_ModelControler {
    private Equipment_ModelRepository repository;

    public Equipment_ModelControler(Equipment_ModelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Iterable<Equipment_Model> getAllEquipment_Model(){
        return repository.findAll();
    }

    @PostMapping("/create")
    public Equipment_Model create(@RequestBody Equipment_Model obj){
        return repository.save(obj);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Equipment_Model> update(@RequestBody Equipment_Model obj){
        return repository.findById(obj.getId()).map(recordFound -> {
            recordFound.setName(obj.getName());

            Equipment_Model updated = repository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Equipment_Model obj){
        repository.delete(obj);
    }
}
