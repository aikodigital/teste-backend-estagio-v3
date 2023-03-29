package com.desafio.aiko.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentStateRequest {
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;

}
