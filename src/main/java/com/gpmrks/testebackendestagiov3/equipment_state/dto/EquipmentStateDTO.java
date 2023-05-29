package com.gpmrks.testebackendestagiov3.equipment_state.dto;

import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class EquipmentStateDTO extends RepresentationModel<EquipmentStateDTO> {

    private UUID id;
    private String name;
    private String color;

    public EquipmentStateDTO(EquipmentState equipmentState) {
        id = equipmentState.getId();
        name = equipmentState.getName();
        color = equipmentState.getColor();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
