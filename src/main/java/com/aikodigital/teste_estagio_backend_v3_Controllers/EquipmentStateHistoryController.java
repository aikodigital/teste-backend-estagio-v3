package com.aikodigital.teste_estagio_backend_v3_Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipmentStateHistory")
public class EquipmentStateHistoryController {

    private final EquipmentStateHistoryService equipmentStateHistoryService;

    public EquipmentStateHistoryController(EquipmentStateHistoryService equipmentStateHistoryService) {
        this.equipmentStateHistoryService = equipmentStateHistoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentStateHistory> findById(@PathVariable int id) {
        EquipmentStateHistory equipmentStateHistory = equipmentStateHistoryService.findById(id);
        return ResponseEntity.ok(equipmentStateHistory);
    }

    @GetMapping("/equipment/{id}")
    public ResponseEntity<List<EquipmentStateHistory>> findByEquipmentId(@PathVariable int id) {
        List<EquipmentStateHistory> equipmentStateHistories = equipmentStateHistoryService.findByEquipmentId(id);
        return ResponseEntity.ok(equipmentStateHistories);
    }

    @PostMapping
    public ResponseEntity<EquipmentStateHistory> create(@Valid @RequestBody EquipmentStateHistory equipmentStateHistory) {
        EquipmentStateHistory createdEquipmentStateHistory = equipmentStateHistoryService.create(equipmentStateHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipmentStateHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentStateHistory> update(@PathVariable int id, @Valid @RequestBody EquipmentStateHistory equipmentStateHistory) {
        EquipmentStateHistory updatedEquipmentStateHistory = equipmentStateHistoryService.update(id, equipmentStateHistory);
        return ResponseEntity.ok(updatedEquipmentStateHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        equipmentStateHistoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
