package com.api.aikobackendteste.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipmentPositionHistoryDto {
    @NotNull
    @NotBlank
    @Max(90)
    @Min(-90)
    private double lat;
    @NotNull
    @NotBlank
    @Max(90)
    @Min(-90)
    private double lon;
}
