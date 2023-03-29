package com.aiko.martins.matheus.equipments.resources;

import com.aiko.martins.matheus.equipments.domain.Equipment_model_state_hourly_earnings;
import com.aiko.martins.matheus.equipments.service.Equipment_model_state_hourly_earningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Equipment_model_state_hourly_earningsController {

    @Autowired
    private Equipment_model_state_hourly_earningsService service;

    @GetMapping("/equipments/hourly_earnings")
    public List<Equipment_model_state_hourly_earnings> list(){
        return service.listAll();
    }

    @GetMapping("/equipments/hourly_earnings/{id}")
    public ResponseEntity<Equipment_model_state_hourly_earnings> get(@PathVariable Integer id) {
        try {
            Equipment_model_state_hourly_earnings equipment = service.get(id);
            return new ResponseEntity<Equipment_model_state_hourly_earnings>(equipment, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<Equipment_model_state_hourly_earnings>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/equipments/hourly_earnings")
    public void add(@RequestBody Equipment_model_state_hourly_earnings equipment) {
        service.save(equipment);
    }

    @PutMapping("/equipments/hourly_earnings/{id}")
    public ResponseEntity<?> update(@RequestBody Equipment_model_state_hourly_earnings equipment, @PathVariable Integer id) {
        try {
            Equipment_model_state_hourly_earnings existEquipment = service.get(id);
            service.save(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/equipments/hourly_earnings/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}