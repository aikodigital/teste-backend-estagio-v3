package com.gpmrks.testebackendestagiov3.equipment_state_history.dto;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;

import java.time.LocalDate;

public class EquipmentStateHistoryDTO {

    private Equipment equipment;
    private LocalDate date;
    private EquipmentState equipmentState;

    public EquipmentStateHistoryDTO(Equipment equipment, LocalDate date, EquipmentState equipmentState) {
        this.equipment = equipment;
        this.date = date;
        this.equipmentState = equipmentState;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public LocalDate getDate() {
        return date;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }
}
