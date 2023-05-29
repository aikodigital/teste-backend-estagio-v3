package com.gpmrks.testebackendestagiov3.equipment.dto;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class EquipmentDTO extends RepresentationModel<EquipmentDTO> {

    private UUID id;
    private EquipmentModel equipmentModel;
    private String name;

    public EquipmentDTO(Equipment equipment) {
        id = equipment.getId();
        equipmentModel = equipment.getEquipmentModel();
        name = equipment.getName();
    }

    public UUID getId() {
        return id;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public String getName() {
        return name;
    }
}
