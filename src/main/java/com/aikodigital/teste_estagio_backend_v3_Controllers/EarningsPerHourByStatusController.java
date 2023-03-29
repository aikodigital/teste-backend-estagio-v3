package com.aikodigital.teste_estagio_backend_v3_Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aikodigital.teste_estagio_backend_v3_Models.Equipment_Model;

@RestController
@RequestMapping("/equipment-models")
public class EarningsPerHourByStatusController {

    @Autowired
    private EarningsPerHourByStatusController equipmentModelRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Equipment_Model> getEquipmentModel(@PathVariable(value = "id") Long equipmentModelId) throws ResourceNotFoundException {
        Equipment_Model equipmentModel = equipmentModelRepository.findById(equipmentModelId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment model not found for this id :: " + equipmentModelId));
        return ResponseEntity.ok().body(equipmentModel);
    }

    @GetMapping("/")
    public List<Equipment_Model> getAllEquipmentModels() {
        return equipmentModelRepository.findAll();
    }

    @PostMapping("/")
    public Equipment_Model createEquipmentModel(@Valid @RequestBody Equipment_Model equipmentModel) {
        return equipmentModelRepository.save(equipmentModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment_Model> updateEquipmentModel(@PathVariable(value = "id") Long equipmentModelId, @Valid @RequestBody Equipment_Model equipmentModelDetails) throws ResourceNotFoundException {
        Equipment_Model equipmentModel = equipmentModelRepository.findById(equipmentModelId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment model not found for this id :: " + equipmentModelId));

        equipmentModel.setName(equipmentModelDetails.getName());

        final Equipment_Model updatedEquipmentModel = equipmentModelRepository.save(equipmentModel);
        return ResponseEntity.ok(updatedEquipmentModel);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEquipmentModel(@PathVariable(value = "id") Long equipmentModelId) throws ResourceNotFoundException {
        EquipmentModel equipmentModel = equipmentModelRepository.findById(equipmentModelId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment model not found for this id :: " + equipmentModelId));

        equipmentModelRepository.delete(equipmentModel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
