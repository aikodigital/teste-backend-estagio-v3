package com.aiko.martins.matheus.equipments.resources;

import com.aiko.martins.matheus.equipments.domain.Equipment;
import com.aiko.martins.matheus.equipments.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService service;

    @GetMapping("/equipments")
    public List<Equipment> list(){
        return service.listAll();
    }

    @GetMapping("/equipments/{id}")
    public ResponseEntity<Equipment> get(@PathVariable Integer id) {
        try {
            Equipment equipment = service.get(id);
            return new ResponseEntity<Equipment>(equipment, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<Equipment>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/equipments")
    public void add(@RequestBody Equipment equipment) {
        service.save(equipment);
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<?> update(@RequestBody Equipment equipment, @PathVariable Integer id) {
        try {
            Equipment existEquipment = service.get(id);
            service.save(equipment);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/equipments/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
