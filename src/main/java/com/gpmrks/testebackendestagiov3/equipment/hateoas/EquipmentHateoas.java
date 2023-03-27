package com.gpmrks.testebackendestagiov3.equipment.hateoas;

import com.gpmrks.testebackendestagiov3.equipment.controller.EquipmentController;
import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentHateoas {

    public static void toHateoas(UUID equipmentId, EquipmentDTO equipmentDTO) {
        equipmentDTO.add(linkTo(methodOn(EquipmentController.class).getEquipmentById(equipmentId)).withSelfRel());
        equipmentDTO.add(linkTo(methodOn(EquipmentController.class).getAllEquipments()).withRel(IanaLinkRelations.COLLECTION));
        equipmentDTO.add(linkTo(methodOn(EquipmentController.class).deleteEquipment(equipmentId)).withRel("delete"));
        equipmentDTO.add(linkTo(methodOn(EquipmentController.class).getEquipmentByModelId(equipmentDTO.getEquipmentModel().getId())).withRel("equipment model"));
    }

    public static void toHateoasList(List<EquipmentDTO> equipmentDTOS) {
        for (EquipmentDTO equipmentDTO : equipmentDTOS) {
            EquipmentHateoas.toHateoas(equipmentDTO.getId(), equipmentDTO);
        }
    }
}
