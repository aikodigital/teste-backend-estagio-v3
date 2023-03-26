package com.gpmrks.testebackendestagiov3.equipment_model.dto;

import java.util.UUID;

public class EquipmentModelDTO {

    private UUID id;
    private String name;

    public EquipmentModelDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
