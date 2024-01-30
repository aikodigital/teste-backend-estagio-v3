package me.dri.aiko.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
@AllArgsConstructor
@Data
public class EquipmentModelStateHourlyEarnings {

    public EquipmentModelStateHourlyEarnings() {

    }

    @EmbeddedId
    private EquipmentModelStateHourlyEarningsId id;

    private Double value;
}
