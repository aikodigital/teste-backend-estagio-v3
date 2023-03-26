package com.gpmrks.testebackendestagiov3.equipment_model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EquipmentModelForm {

    private String name;

    @JsonCreator
    public EquipmentModelForm(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
