package com.gpmrks.testebackendestagiov3.equipment_state.dto;

import jakarta.validation.constraints.NotNull;

public class EquipmentStateForm {

    @NotNull(message = "Name is required!")
    private String name;

    @NotNull(message = "Color is required!")
    private String color;

    public EquipmentStateForm(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

}
