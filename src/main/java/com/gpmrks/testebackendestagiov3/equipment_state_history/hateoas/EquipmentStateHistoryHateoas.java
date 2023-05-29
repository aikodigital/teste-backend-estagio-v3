package com.gpmrks.testebackendestagiov3.equipment_state_history.hateoas;

import com.gpmrks.testebackendestagiov3.equipment_state_history.controller.EquipmentStateHistoryController;
import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentStateHistoryHateoas {

    public static void toHateoas(UUID equipmentId, UUID stateId, EquipmentStateHistoryDTO equipmentStateHistoryDTO) {
        equipmentStateHistoryDTO.add(linkTo(methodOn(EquipmentStateHistoryController.class).getEquipmentStateHistoryByEquipmentId(equipmentId)).withRel("collection - equipment"));
        equipmentStateHistoryDTO.add(linkTo(methodOn(EquipmentStateHistoryController.class).getEquipmentStateHistoryByStateId(stateId)).withRel("collection - equipment state"));
        equipmentStateHistoryDTO.add(linkTo(methodOn(EquipmentStateHistoryController.class).getAllEquipmentsStateHistories()).withRel(IanaLinkRelations.COLLECTION));
        equipmentStateHistoryDTO.add(linkTo(methodOn(EquipmentStateHistoryController.class).deleteEquipmentStateHistory(equipmentId)).withRel("delete - equipment state history"));
    }

    public static void toHateoasList(List<EquipmentStateHistoryDTO> equipmentStateHistoryDTOS) {
        for (EquipmentStateHistoryDTO equipmentStateHistoryDTO : equipmentStateHistoryDTOS) {
            EquipmentStateHistoryHateoas.toHateoas(equipmentStateHistoryDTO.getEquipment().getId(), equipmentStateHistoryDTO.getEquipmentState().getId(), equipmentStateHistoryDTO);
        }
    }
}
