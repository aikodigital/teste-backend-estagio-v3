package com.gpmrks.testebackendestagiov3.equipment.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;
public class EquipmentForm {

    @NotNull(message = "Name is required!")
    private String name;

    @NotNull(message = "Equipment Model ID is required!")
    private UUID equipmentModelId;
    public EquipmentForm(String name, UUID equipmentModelId) {
        this.name = name;
        this.equipmentModelId = equipmentModelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getEquipmentModelId() {
        return equipmentModelId;
    }
}
