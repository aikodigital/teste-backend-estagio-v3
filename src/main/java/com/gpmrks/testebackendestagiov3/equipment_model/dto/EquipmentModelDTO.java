package com.gpmrks.testebackendestagiov3.equipment_model.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class EquipmentModelDTO extends RepresentationModel<EquipmentModelDTO> {

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
