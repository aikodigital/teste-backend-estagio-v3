package aelton.teste.teste.estagio.controller;

import aelton.teste.teste.estagio.entity.EquipmentModel;
import aelton.teste.teste.estagio.service.equipmentModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipmentModel")
public class equipmentModelController {
    @Autowired
    private  equipmentModelService equipmentModelService;

    @GetMapping
    public List<EquipmentModel> listar() {
        return equipmentModelService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentModel create(@RequestBody EquipmentModel equipmentModel) {
        return equipmentModelService.create(equipmentModel);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentModel> equipmentModelFindById(@PathVariable Long id) {
        EquipmentModel equipmentModel = equipmentModelService.findById(id);
        return ResponseEntity.ok(equipmentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        equipmentModelService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public EquipmentModel update(@PathVariable Long id, @RequestBody EquipmentModel equipmentCreate) {
        return equipmentModelService.update(id, equipmentCreate);


    }
}
