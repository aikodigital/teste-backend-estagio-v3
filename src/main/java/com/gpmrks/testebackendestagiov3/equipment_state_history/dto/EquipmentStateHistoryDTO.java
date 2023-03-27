package com.gpmrks.testebackendestagiov3.equipment_state_history.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class EquipmentStateHistoryDTO extends RepresentationModel<EquipmentStateHistoryDTO> {

    private Equipment equipment;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;
    private EquipmentState equipmentState;

    public EquipmentStateHistoryDTO(Equipment equipment, LocalDateTime date, EquipmentState equipmentState) {
        this.equipment = equipment;
        this.date = date;
        this.equipmentState = equipmentState;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }
}
