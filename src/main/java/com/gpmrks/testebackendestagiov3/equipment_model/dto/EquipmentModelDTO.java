package com.gpmrks.testebackendestagiov3.equipment_model.dto;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class EquipmentModelDTO extends RepresentationModel<EquipmentModelDTO> {

    private UUID id;
    private String name;

    public EquipmentModelDTO(EquipmentModel equipmentModel) {
        id = equipmentModel.getId();
        name = equipmentModel.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
