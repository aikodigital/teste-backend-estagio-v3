package com.aiko.martins.matheus.equipments.resources;


import com.aiko.martins.matheus.equipments.domain.Equipment_model;
import com.aiko.martins.matheus.equipments.service.Equipment_modelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Equipment_modelController {
    @Autowired
    private Equipment_modelService service;
    @GetMapping("/equipments/models")
    public List<Equipment_model> list(){
        return service.listAll();
    }

    @GetMapping("/equipments/models/{id}")
    public ResponseEntity<Equipment_model> get(@PathVariable Integer id) {
        try {
            Equipment_model equipment = service.get(id);
            return new ResponseEntity<Equipment_model>(equipment, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<Equipment_model>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/equipments/models")
    public void add(@RequestBody Equipment_model equipment) {
        service.save(equipment);
    }

    @PutMapping("/equipments/models/{id}")
    public ResponseEntity<?> update(@RequestBody Equipment_model equipment, @PathVariable Integer id) {
        try {
            Equipment_model existEquipment = service.get(id);
            service.save(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/equipments/models/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
