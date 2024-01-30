package me.dri.aiko.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class EquipmentModelStateHourlyEarningsId implements Serializable {

    public EquipmentModelStateHourlyEarningsId() {

    }

    @ManyToOne
    @JoinColumn(name = "equipmentModelId", referencedColumnName = "id")
    private EquipmentModel equipmentModel;

    @ManyToOne
    @JoinColumn(name = "equipmentStateId", referencedColumnName = "id")
    private EquipmentState equipmentState;

}
