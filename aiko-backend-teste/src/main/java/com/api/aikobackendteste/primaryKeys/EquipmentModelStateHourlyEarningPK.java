package com.api.aikobackendteste.primaryKeys;

import com.api.aikobackendteste.models.EquipmentModelModel;
import com.api.aikobackendteste.models.EquipmentStateModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class EquipmentModelStateHourlyEarningPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "equipment_model_id", referencedColumnName = "id")
    private EquipmentModelModel equipmentModel;

    @ManyToOne
    @JoinColumn(name = "equipment_state_id", referencedColumnName = "id")
    private EquipmentStateModel equipmentState;

    public EquipmentModelStateHourlyEarningPK() {}

    public EquipmentModelStateHourlyEarningPK(EquipmentModelModel equipmentModelModel, EquipmentStateModel equipmentState) {
        this.equipmentModel = equipmentModelModel;
        this.equipmentState = equipmentState;
    }

    public EquipmentModelModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModelModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public EquipmentStateModel getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentStateModel equipmentState) {
        this.equipmentState = equipmentState;
    }

}