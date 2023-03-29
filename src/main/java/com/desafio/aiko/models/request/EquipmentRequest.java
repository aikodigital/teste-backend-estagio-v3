package com.desafio.aiko.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentRequest {
    private String id;
    private String name;
    @JsonProperty(value = "equipment_model_id")
    private UUID equipmentModel;
}
