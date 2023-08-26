package com.gpmrks.testebackendestagiov3.equipment_model.hateoas;

import com.gpmrks.testebackendestagiov3.equipment_model.controller.EquipmentModelController;
import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelDTO;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentModelHateoas {

    public static void toHateoas(UUID equipmentModelId, EquipmentModelDTO equipmentModelDTO) {
        equipmentModelDTO.add(linkTo(methodOn(EquipmentModelController.class).getEquipmentModelById(equipmentModelId)).withSelfRel());
        equipmentModelDTO.add(linkTo(methodOn(EquipmentModelController.class).getAllEquipmentsModels()).withRel(IanaLinkRelations.COLLECTION));
        equipmentModelDTO.add(linkTo(methodOn(EquipmentModelController.class).deleteEquipmentModel(equipmentModelId)).withRel("delete"));
    }

    public static void toHateoasList(List<EquipmentModelDTO> equipmentModelDTOS) {
        for (EquipmentModelDTO equipmentModelDTO : equipmentModelDTOS) {
            EquipmentModelHateoas.toHateoas(equipmentModelDTO.getId(), equipmentModelDTO);
        }
    }

}
