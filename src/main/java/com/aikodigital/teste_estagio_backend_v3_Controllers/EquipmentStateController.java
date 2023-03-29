package com.aikodigital.teste_estagio_backend_v3_Controllers;

package com.aikodigital.teste_estagio_backend_v3_Controllers;

import com.aikodigital.teste_estagio_backend_v3_Models.Equipment_State;
import com.aikodigital.teste_estagio_backend_v3_Repositories.EquipmentStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipmentstate")
@Validated
public class EquipmentStateController {

    @Autowired
    private EquipmentStateRepository equipmentStateRepository;

    @GetMapping
    public List<Equipment_State> getAllEquipmentStates() {
        return equipmentStateRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment_State> getEquipmentStateById(@PathVariable int id) {
        Optional<Equipment_State> equipmentState = equipmentStateRepository.findById(id);
        return equipmentState.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Equipment_State> createEquipmentState(@RequestBody @Valid Equipment_State equipmentState) {
        Equipment_State savedEquipmentState = equipmentStateRepository.save(equipmentState);
        return new ResponseEntity<>(savedEquipmentState, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment_State> updateEquipmentState(@PathVariable int id, @RequestBody @Valid Equipment_State equipmentState) {
        Optional<Equipment_State> optionalEquipmentState = equipmentStateRepository.findById(id);
        if (optionalEquipmentState.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Equipment_State existingEquipmentState = optionalEquipmentState.get();
        existingEquipmentState.setDescription(equipmentState.getDescription());
        Equipment_State savedEquipmentState = equipmentStateRepository.save(existingEquipmentState);
        return new ResponseEntity<>(savedEquipmentState, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEquipmentState(@PathVariable int id) {
        Optional<Equipment_State> optionalEquipmentState = equipmentStateRepository.findById(id);
        if (optionalEquipmentState.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        equipmentStateRepository.delete(optionalEquipmentState.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
