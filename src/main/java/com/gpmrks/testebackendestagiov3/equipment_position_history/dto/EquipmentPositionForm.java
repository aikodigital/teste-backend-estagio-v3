package com.gpmrks.testebackendestagiov3.equipment_position_history.dto;

import jakarta.validation.constraints.NotNull;

public class EquipmentPositionForm {

    @NotNull(message = "Latitude is required!")
    private float lat;

    @NotNull(message = "Longitude is required!")
    private float lon;

    public EquipmentPositionForm(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

}
