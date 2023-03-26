package com.gpmrks.testebackendestagiov3.equipment_position_history.entity;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;

import java.io.Serializable;
import java.time.LocalDate;

public class EquipmentPositionHistoryId implements Serializable {

    private Equipment equipment;

    private LocalDate date;

    public EquipmentPositionHistoryId() {
    }

    public EquipmentPositionHistoryId(Equipment equipment, LocalDate date) {
        this.equipment = equipment;
        this.date = date;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
