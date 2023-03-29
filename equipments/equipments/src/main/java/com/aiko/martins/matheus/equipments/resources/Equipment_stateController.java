package com.aiko.martins.matheus.equipments.resources;

import com.aiko.martins.matheus.equipments.domain.Equipment_state;
import com.aiko.martins.matheus.equipments.service.Equipment_stateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Equipment_stateController {
    @Autowired
    private Equipment_stateService service;

    @GetMapping("/equipments/states")
    public List<Equipment_state> list(){
        return service.listAll();
    }

    @GetMapping("/equipments/states/{id}")
    public ResponseEntity<Equipment_state> get(@PathVariable Integer id) {
        try {
            Equipment_state equipment = service.get(id);
            return new ResponseEntity<Equipment_state>(equipment, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<Equipment_state>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/equipments/states")
    public void add(@RequestBody Equipment_state equipment) {
        service.save(equipment);
    }

    @PutMapping("/equipments/states/{id}")
    public ResponseEntity<?> update(@RequestBody Equipment_state equipment, @PathVariable Integer id) {
        try {
            Equipment_state existEquipment = service.get(id);
            service.save(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/equipments/states/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
