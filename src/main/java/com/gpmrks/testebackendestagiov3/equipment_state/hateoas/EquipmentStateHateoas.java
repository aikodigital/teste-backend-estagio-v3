package com.gpmrks.testebackendestagiov3.equipment_state.hateoas;

import com.gpmrks.testebackendestagiov3.equipment_state.controller.EquipmentStateController;
import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentStateHateoas {

    public static void toHateoas(UUID stateId, EquipmentStateDTO equipmentStateDTO) {
        equipmentStateDTO.add(linkTo(methodOn(EquipmentStateController.class).getEquipmentStateById(stateId)).withSelfRel());
        equipmentStateDTO.add(linkTo(methodOn(EquipmentStateController.class).getAllEquipmentsStates()).withRel(IanaLinkRelations.COLLECTION));
        equipmentStateDTO.add(linkTo(methodOn(EquipmentStateController.class).deleteEquipmentState(stateId)).withRel("delete"));
    }

    public static void toHateoasList(List<EquipmentStateDTO> equipmentStateDTOS) {
        for (EquipmentStateDTO equipmentStateDTO : equipmentStateDTOS) {
            EquipmentStateHateoas.toHateoas(equipmentStateDTO.getId(), equipmentStateDTO);
        }
    }

}
