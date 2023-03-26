package com.gpmrks.testebackendestagiov3.equipment_state.dto;

public class EquipmentStateForm {

    private String name;
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

    public void setColor(String color) {
        this.color = color;
    }
}
