package me.dri.aiko.controllers;

import me.dri.aiko.entities.dto.EquipmentModelStateHourlyEarningsDTO;
import me.dri.aiko.services.interfaces.EquipmentModelStateHourlyEarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipments/earnings")
public class EquipmentModelStateHourlyEarningsController {

    private final EquipmentModelStateHourlyEarningsService service;

    @Autowired
    public EquipmentModelStateHourlyEarningsController(EquipmentModelStateHourlyEarningsService service) {
        this.service = service;
    }

    @GetMapping
    public List<EquipmentModelStateHourlyEarningsDTO> findAll() {
        return this.service.findAll();
    }
    @GetMapping(path = "/{idEquipmentModel}")
    public ResponseEntity<EquipmentModelStateHourlyEarningsDTO> findEarningsByEquipmentModelName(@PathVariable String idEquipmentModel) {
        return ResponseEntity.ok(this.service.findEquipmentModelHourlyByEquipmentModelName(idEquipmentModel));
    }
}
