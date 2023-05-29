package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class EquipmentModelStateHourlyEarningsForm {

    private float value;

    @JsonCreator
    public EquipmentModelStateHourlyEarningsForm(@JsonProperty("value") float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
