package com.desafio.aiko.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentModelStateHourlyEarningsRequest {
    @JsonProperty("equipment_model_id")
    @Column(name = "equipment_model_id")
    private String equipmentModelId;
    @JsonProperty("equipment_state_id")
    @Column(name = "equipment_state_id")
    private String equipmentStateId;
    private Double value;

}
