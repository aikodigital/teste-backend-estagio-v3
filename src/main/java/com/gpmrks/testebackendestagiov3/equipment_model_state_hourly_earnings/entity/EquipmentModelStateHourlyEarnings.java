package com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.entity;

import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import com.gpmrks.testebackendestagiov3.equipment_model_state_hourly_earnings.dto.EquipmentModelStateHourlyEarningsDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
@IdClass(EquipmentModelStateHourlyEarningsId.class)
public class EquipmentModelStateHourlyEarnings {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_model_id", referencedColumnName = "id")
    private EquipmentModel equipmentModel;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_state_id", referencedColumnName = "id")
    private EquipmentState equipmentState;

    @Column(name = "value")
    private Float value;

    public EquipmentModelStateHourlyEarnings() {
    }

    public EquipmentModelStateHourlyEarnings(EquipmentModel equipmentModel, EquipmentState equipmentState, Float value) {
        this.equipmentModel = equipmentModel;
        this.equipmentState = equipmentState;
        this.value = value;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public EquipmentModelStateHourlyEarningsDTO equipmentModelStateHourlyEarningsToDTO() {
        return new EquipmentModelStateHourlyEarningsDTO(equipmentModel, equipmentState, value);
    }

    @Override
    public String toString() {
        return "EquipmentModelStateHourlyEarnings{" +
                "equipmentModel=" + equipmentModel +
                ", equipmentState=" + equipmentState +
                ", value=" + value +
                '}';
    }
}
