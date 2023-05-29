package com.gpmrks.testebackendestagiov3.equipment_position_history.entity;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EquipmentPositionHistoryId implements Serializable {

    private Equipment equipment;

    private LocalDateTime date;

    public EquipmentPositionHistoryId() {
    }

    public EquipmentPositionHistoryId(Equipment equipment, LocalDateTime date) {
        this.equipment = equipment;
        this.date = date;
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
}
