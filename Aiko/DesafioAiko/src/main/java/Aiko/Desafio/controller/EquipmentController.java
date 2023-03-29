package Aiko.Desafio.controller;


import Aiko.Desafio.dto.EquipmentDTO;
import Aiko.Desafio.dto.EquipmentSaveDTO;
import Aiko.Desafio.dto.EquipmentUpdateDTO;
import Aiko.Desafio.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/v1/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(path = "/save")
    public String saveEquipment(@RequestBody EquipmentSaveDTO equipmentSaveDTO){

        String id = equipmentService.addEquipment(equipmentSaveDTO);
        return id;
    }
    @GetMapping(path = "/getAllEquipment")
    public List<EquipmentDTO> getAllEquipment(){

        List<EquipmentDTO>allEquipments = equipmentService.getAllEquipment();
        return allEquipments;
    }
    @PutMapping(path = "/update")
    public String updateEquipment(@RequestBody EquipmentUpdateDTO equipmentUpdateDTO){

        String id = equipmentService.updateEquipments(equipmentUpdateDTO);
        return id;
    }
    @DeleteMapping(path = "/deleteEquipment/{Id}")
    public String deleteEquipment(@PathVariable(value = "Id") int Id){

        boolean deleteEquipments = equipmentService.deleteEquipment(Id);
        return "deleted";
    }


    }


