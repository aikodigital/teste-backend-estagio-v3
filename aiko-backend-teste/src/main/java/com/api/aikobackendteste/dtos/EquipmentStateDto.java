package com.api.aikobackendteste.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipmentStateDto {
    @NotNull
    @NotBlank
    private String color;
    @NotNull
    @NotBlank
    private String name;
}
