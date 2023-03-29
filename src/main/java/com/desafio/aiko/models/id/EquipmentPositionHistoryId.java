package com.desafio.aiko.models.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
@Builder
public class EquipmentPositionHistoryId implements Serializable {
    @EqualsAndHashCode.Include
    @Column(name = "equipment_id", updatable = false, insertable = false)
    private UUID equipmentId;

}
