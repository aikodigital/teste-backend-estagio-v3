package com.gpmrks.testebackendestagiov3.equipment_position_history.hateoas;

import com.gpmrks.testebackendestagiov3.equipment_position_history.controller.EquipmentPositionHistoryController;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentPositionHistoryHateoas {

    public static void toHateoas(UUID equipmentId, EquipmentPositionHistoryDTO equipmentPositionHistoryDTO) {
        equipmentPositionHistoryDTO.add(linkTo(methodOn(EquipmentPositionHistoryController.class).deleteEquipmentPositionHistory(equipmentId)).withRel("delete - equipment position history"));
        equipmentPositionHistoryDTO.add(linkTo(methodOn(EquipmentPositionHistoryController.class).getAllEquipmentsPositionsHistories()).withRel(IanaLinkRelations.COLLECTION));
    }

    public static void toHateoasList(List<EquipmentPositionHistoryDTO> equipmentPositionHistoryDTOS) {
        for (EquipmentPositionHistoryDTO equipmentPositionHistoryDTO : equipmentPositionHistoryDTOS) {
            EquipmentPositionHistoryHateoas.toHateoas(equipmentPositionHistoryDTO.getEquipment().getId(), equipmentPositionHistoryDTO);
        }
    }

}
