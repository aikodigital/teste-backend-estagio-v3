package com.gpmrks.testebackendestagiov3.equipment_state.dto;

import java.util.UUID;

public class EquipmentStateDTO {

    private UUID id;
    private String name;
    private String color;

    public EquipmentStateDTO(UUID id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
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
