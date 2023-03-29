package com.desafio.aiko.models.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentPositionHistoryRequest {
    private Timestamp date;
    @JsonProperty("equipment_id")
    private UUID equipmentId;
    private Double lat;
    private Double lon;
}
