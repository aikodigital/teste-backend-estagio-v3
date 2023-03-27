package br.com.aikodigital.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aikodigital.api.models.Equipment_MSHE;
import br.com.aikodigital.api.repositorys.Equipment_MSHE_Repository;

@RestController
@RequestMapping("/equipments_mshe")
public class Equipment_MSHE_Controller {
    private Equipment_MSHE_Repository repository;

    public Equipment_MSHE_Controller(Equipment_MSHE_Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Iterable<Equipment_MSHE> getAllEquipment_MSHE(){
        return repository.findAll();
    }

    @PostMapping("/create")
    public Equipment_MSHE create(@RequestBody Equipment_MSHE obj){
        return repository.save(obj);
    }

    @PutMapping("/update")
    public ResponseEntity<Equipment_MSHE> update(@RequestBody Equipment_MSHE obj){
        return repository.findById(obj.getId()).map(recordFound -> {
            recordFound.setValue(obj.getValue());

            Equipment_MSHE updated = repository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }
   
    @DeleteMapping("/delete")
    public void delete(@RequestBody Equipment_MSHE obj){
        repository.delete(obj);
    }
}
