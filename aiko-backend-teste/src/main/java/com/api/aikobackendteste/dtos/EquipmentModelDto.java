package com.api.aikobackendteste.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipmentModelDto {
    @NotNull
    @NotBlank
    private String name;
}
