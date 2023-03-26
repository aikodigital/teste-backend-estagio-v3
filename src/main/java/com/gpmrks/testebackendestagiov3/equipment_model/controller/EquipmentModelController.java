package com.gpmrks.testebackendestagiov3.equipment_model.controller;

import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelDTO;
import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelForm;
import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model.service.EquipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("equipments-models")
public class EquipmentModelController {

    private EquipmentModelService equipmentModelService;

    @Autowired
    public EquipmentModelController(EquipmentModelService equipmentModelService) {
        this.equipmentModelService = equipmentModelService;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentModelDTO>> getAllEquipmentsModels() {
        List<EquipmentModel> equipmentModels = equipmentModelService.getAllEquipmentsModels();
        List<EquipmentModelDTO> equipmentModelDTOS = equipmentModels.stream()
                .map(EquipmentModel::equipmentModelToDTO)
                .toList();
        return ResponseEntity.ok().body(equipmentModelDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<EquipmentModelDTO> getEquipmentModelById(@PathVariable UUID id) {
        EquipmentModel equipmentModel = equipmentModelService.getEquipModelById(id);
        EquipmentModelDTO equipmentModelDTO = equipmentModel.equipmentModelToDTO();
        return ResponseEntity.ok().body(equipmentModelDTO);
    }

    @PostMapping
    public ResponseEntity<EquipmentModelDTO> createEquipmentModel(@RequestBody EquipmentModelForm equipmentModelToSave, UriComponentsBuilder uriComponentsBuilder) {
        EquipmentModel equipmentModel = equipmentModelService.createEquipmentModel(equipmentModelToSave);
        EquipmentModelDTO equipmentModelDTO = equipmentModel.equipmentModelToDTO();
        URI uri = uriComponentsBuilder.path("/equipments-models/{id}").buildAndExpand(equipmentModel.getId()).toUri();
        return ResponseEntity.created(uri).body(equipmentModelDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<EquipmentModelDTO> updateEquipment(@PathVariable UUID id, @RequestBody EquipmentModelForm updatedEquipmentModel) {
        EquipmentModel equipmentModel = equipmentModelService.updateEquipmentModel(id, updatedEquipmentModel);
        EquipmentModelDTO equipmentModelDTO = equipmentModel.equipmentModelToDTO();
        return ResponseEntity.ok().body(equipmentModelDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEquipmentModel(@PathVariable UUID id) {
        equipmentModelService.deleteEquipmentModel(id);
        return ResponseEntity.noContent().build();
    }
}
