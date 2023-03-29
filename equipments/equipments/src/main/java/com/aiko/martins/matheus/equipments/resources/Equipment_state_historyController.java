package com.aiko.martins.matheus.equipments.resources;

import com.aiko.martins.matheus.equipments.domain.Equipment_state_history;
import com.aiko.martins.matheus.equipments.service.Equipment_state_historyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Equipment_state_historyController {
    @Autowired
    private Equipment_state_historyService service;

    /*ENDPOINT*/
    @GetMapping("/equipments/states_history")
    public ResponseEntity<List<Equipment_state_history>> findByOrderByDateDesc(){
        List<Equipment_state_history> result = service.findByOrderByDateDesc();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/equipments/states_history/{id}")
    public ResponseEntity<Equipment_state_history> get(@PathVariable Integer id) {
        try {
            Equipment_state_history equipment = service.get(id);
            return new ResponseEntity<Equipment_state_history>(equipment, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<Equipment_state_history>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/equipments/states_history")
    public void add(@RequestBody Equipment_state_history equipment) {
        service.save(equipment);
    }

    @PutMapping("/equipments/states_history/{id}")
    public ResponseEntity<?> update(@RequestBody Equipment_state_history equipment, @PathVariable Integer id) {
        try {
            Equipment_state_history existEquipment = service.get(id);
            service.save(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/equipments/states_history/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
