package com.desafio.aiko.models.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Embeddable
@Builder
@AllArgsConstructor
public class EquipmentModelStateHourlyEarningsId implements Serializable {
    @EqualsAndHashCode.Include
    @Column(name = "equipment_model_id", updatable = false, insertable = false)
    private UUID equipmentModelId;
    @EqualsAndHashCode.Include
    @Column(name = "equipment_state_id", updatable = false, insertable = false)
    private UUID equipmentStateId;

}
