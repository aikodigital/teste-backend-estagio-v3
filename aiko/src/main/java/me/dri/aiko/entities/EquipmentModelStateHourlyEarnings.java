package me.dri.aiko.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
@Data
public class EquipmentModelStateHourlyEarnings {

    @Id
    @ManyToOne
    @JoinColumn(name = "equipmentModelId", referencedColumnName = "id")
    private EquipmentModel equipmentModel;


    @Id
    @ManyToOne
    @JoinColumn(name = "equipmentStateId", referencedColumnName = "id")
    private EquipmentState equipmentState;

    private Double value;
}
