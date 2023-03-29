package aelton.teste.teste.estagio.controller;

import aelton.teste.teste.estagio.dto.EquipmentDto;
import aelton.teste.teste.estagio.entity.Equipment;
import aelton.teste.teste.estagio.service.equipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class equipmentController {
    @Autowired
    private equipmentService equipmentService;


    @GetMapping
    public List<EquipmentDto> listar() {
        List<Equipment>equipment = equipmentService.findAll();
        return EquipmentDto. convert(equipment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment create(@RequestBody Equipment equipment) {
        return equipmentService.create(equipment);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDto> equipmentFindById(@PathVariable Long id) {
       Equipment equipment = equipmentService.findById(id);

        return ResponseEntity.ok(new EquipmentDto(equipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        equipmentService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public Equipment update(@PathVariable Long id, @RequestBody Equipment equipmentCreate) {
        return equipmentService.update(id, equipmentCreate);


    }

}
