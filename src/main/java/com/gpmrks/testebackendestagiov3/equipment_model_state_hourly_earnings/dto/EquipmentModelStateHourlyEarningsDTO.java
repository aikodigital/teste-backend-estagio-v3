package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import org.springframework.hateoas.RepresentationModel;

public class EquipmentModelStateHourlyEarningsDTO extends RepresentationModel<EquipmentModelStateHourlyEarningsDTO> {

    private EquipmentModel equipmentModel;
    private EquipmentState equipmentState;
    private Float value;

    public EquipmentModelStateHourlyEarningsDTO(EquipmentModel equipmentModel, EquipmentState equipmentState, Float value) {
        this.equipmentModel = equipmentModel;
        this.equipmentState = equipmentState;
        this.value = value;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public Float getValue() {
        return value;
    }
}
