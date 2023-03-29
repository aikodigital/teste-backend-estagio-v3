package com.desafio.aiko.models.entities;

import com.desafio.aiko.models.id.EquipmentModelStateHourlyEarningsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipment_model_state_hourly_earnings", schema = "operation")
public class EquipmentModelStateHourlyEarnings implements Serializable {
    @EmbeddedId
    private EquipmentModelStateHourlyEarningsId id;

    private Double value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_model_id", referencedColumnName = "id", insertable = false, updatable = false)
    private EquipmentModel equipmentModel;

}
