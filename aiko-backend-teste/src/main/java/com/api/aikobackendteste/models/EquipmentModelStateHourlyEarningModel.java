package com.api.aikobackendteste.models;

import com.api.aikobackendteste.primaryKeys.EquipmentModelStateHourlyEarningPK;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "equipment_model_state_hourly_earnings")
@Table(name="equipment_model_state_hourly_earnings")
public class EquipmentModelStateHourlyEarningModel implements Serializable {
    @EmbeddedId
    private EquipmentModelStateHourlyEarningPK id;
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    private double value;

    public EquipmentModelModel getEquipmentModelModel() {
        return this.id.getEquipmentModel();
    }

    public void setEquipmentModelModel(EquipmentModelModel equipmentModelModel) {
        this.id.setEquipmentModel( equipmentModelModel);
    }

    public EquipmentStateModel getEquipmentStateModel() {
        return this.id.getEquipmentState();
    }

    public void setEquipmentStateModel(EquipmentStateModel equipmentStateModel) {
        this.id.setEquipmentState(equipmentStateModel);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
