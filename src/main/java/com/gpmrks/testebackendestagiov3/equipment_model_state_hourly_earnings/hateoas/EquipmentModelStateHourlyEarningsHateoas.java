package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.hateoas;

import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.controller.EquipmentModelStateHourlyEarningsController;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentModelStateHourlyEarningsHateoas {

    public static void toHateoas(UUID modelId, UUID stateId, EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsDTO) {
        equipmentModelStateHourlyEarningsDTO.add(linkTo(methodOn(EquipmentModelStateHourlyEarningsController.class).getEquipmentsModelsStatesHourlyEarningsByModelAndStateIds(modelId, stateId)).withSelfRel());
        equipmentModelStateHourlyEarningsDTO.add(linkTo(methodOn(EquipmentModelStateHourlyEarningsController.class).getEquipmentsModelsStatesHourlyEarningsByModelId(modelId)).withRel("collection - equipment model"));
        equipmentModelStateHourlyEarningsDTO.add(linkTo(methodOn(EquipmentModelStateHourlyEarningsController.class).getEquipmentsModelsStatesHourlyEarningsByStateId(stateId)).withRel("collection - equipment state"));
        equipmentModelStateHourlyEarningsDTO.add(linkTo(methodOn(EquipmentModelStateHourlyEarningsController.class).getAllEquipmentsModelsStatesHourlyEarnings()).withRel(IanaLinkRelations.COLLECTION));
    }

    public static void toHateoasList(List<EquipmentModelStateHourlyEarningsDTO> equipmentModelStateHourlyEarningsDTOS) {
        for (EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsDTO : equipmentModelStateHourlyEarningsDTOS) {
            EquipmentModelStateHourlyEarningsHateoas.toHateoas(equipmentModelStateHourlyEarningsDTO.getEquipmentModel().getId(), equipmentModelStateHourlyEarningsDTO.getEquipmentState().getId(), equipmentModelStateHourlyEarningsDTO);
        }
    }

}
