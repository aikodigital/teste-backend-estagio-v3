package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;

import java.io.Serializable;
import java.util.Objects;

public class EquipmentModelStateHourlyEarningsId implements Serializable {

    private EquipmentModel equipmentModel;

    private EquipmentState equipmentState;

    public EquipmentModelStateHourlyEarningsId() {
    }

    public EquipmentModelStateHourlyEarningsId(EquipmentModel equipmentModel, EquipmentState equipmentState) {
        this.equipmentModel = equipmentModel;
        this.equipmentState = equipmentState;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentModelStateHourlyEarningsId that = (EquipmentModelStateHourlyEarningsId) o;
        return Objects.equals(equipmentModel, that.equipmentModel) && Objects.equals(equipmentState, that.equipmentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentModel, equipmentState);
    }
}
