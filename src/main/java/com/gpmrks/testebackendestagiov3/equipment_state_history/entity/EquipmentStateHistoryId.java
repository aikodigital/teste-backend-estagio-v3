package com.gpmrks.testebackendestagiov3.equipment_state_history.entity;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EquipmentStateHistoryId implements Serializable {

    private Equipment equipment;

    private LocalDateTime date;

    private EquipmentState equipmentState;

    public EquipmentStateHistoryId() {
    }

    public EquipmentStateHistoryId(Equipment equipment, LocalDateTime date, EquipmentState equipmentState) {
        this.equipment = equipment;
        this.date = date;
        this.equipmentState = equipmentState;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentStateHistoryId that = (EquipmentStateHistoryId) o;
        return Objects.equals(equipment, that.equipment) && Objects.equals(date, that.date) && Objects.equals(equipmentState, that.equipmentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipment, date, equipmentState);
    }
}
