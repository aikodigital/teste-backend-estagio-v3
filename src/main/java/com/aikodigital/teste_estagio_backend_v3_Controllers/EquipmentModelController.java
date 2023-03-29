package com.aikodigital.teste_estagio_backend_v3_Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aikodigital.teste_estagio_backend_v3_Models.Equipment_Model;
import com.aikodigital.teste_estagio_backend_v3_Repositories.EquipmentModelRepository;

@RestController
@RequestMapping("/api/v1/equipment-models")
public class EquipmentModelController {

    @Autowired
    private EquipmentModelService equipmentModelService;

    @PostMapping
    public ResponseEntity<EquipmentModel> createEquipmentModel(@RequestBody EquipmentModel equipmentModel) {
        EquipmentModel savedEquipmentModel = equipmentModelService.createEquipmentModel(equipmentModel);
        return new ResponseEntity<>(savedEquipmentModel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentModel> getEquipmentModelById(@PathVariable int id) {
        EquipmentModel equipmentModel = equipmentModelService.getEquipmentModelById(id);
        return new ResponseEntity<>(equipmentModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentModel>> getAllEquipmentModels() {
        List<EquipmentModel> equipmentModels = equipmentModelService.getAllEquipmentModels();
        return new ResponseEntity<>(equipmentModels, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentModel> updateEquipmentModelById(@PathVariable int id, @RequestBody EquipmentModel equipmentModel) {
        EquipmentModel updatedEquipmentModel = equipmentModelService.updateEquipmentModelById(id, equipmentModel);
        return new ResponseEntity<>(updatedEquipmentModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentModelById(@PathVariable int id) {
        equipmentModelService.deleteEquipmentModelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

