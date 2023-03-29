package com.desafio.aiko.models.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class EquipmentStateHistoryRequest {
    @JsonProperty(value = "equipment_id")
    private UUID equipmentId;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;
    @JsonProperty(value = "equipment_state_id")
    private UUID equipmentStateId;
}
