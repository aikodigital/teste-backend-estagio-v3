package com.gpmrks.testebackendestagiov3.equipment.dto;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;

import java.util.UUID;

public class EquipmentDTO {

    private UUID id;
    private EquipmentModel equipmentModel;
    private String name;

    public EquipmentDTO(UUID id, EquipmentModel equipmentModel, String name) {
        this.id = id;
        this.equipmentModel = equipmentModel;
        this.name = name;
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
