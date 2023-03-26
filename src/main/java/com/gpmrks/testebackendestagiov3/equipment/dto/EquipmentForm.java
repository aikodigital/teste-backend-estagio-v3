package com.gpmrks.testebackendestagiov3.equipment.dto;

import java.util.UUID;
public class EquipmentForm {
    private String name;
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
