package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EquipmentModelStateHourlyEarningsForm {

    @NotNull(message = "Value is required!")
    @NotEmpty(message = "Value is required!")
    @Positive(message = "Value must be greater than zero!")
    private float value;

    @JsonCreator
    public EquipmentModelStateHourlyEarningsForm(@JsonProperty("value") float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
