package com.aikodigital.teste_estagio_backend_v3_Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aikodigital.teste_estagio_backend_v3_Models.Equipment;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        Equipment savedEquipment = equipmentService.createEquipment(equipment);
        return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable int id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipmentById(@PathVariable int id, @RequestBody Equipment equipment) {
        Equipment updatedEquipment = equipmentService.updateEquipmentById(id, equipment);
        return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentById(@PathVariable int id) {
        equipmentService.deleteEquipmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

