package com.gpmrks.testebackendestagiov3.equipment_position_history.dto;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_position_history.entity.EquipmentPositionHistory;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class EquipmentPositionHistoryDTO extends RepresentationModel<EquipmentPositionHistoryDTO> {

    private Equipment equipment;
    private LocalDateTime date;
    private Float lat;
    private Float lon;

    public EquipmentPositionHistoryDTO(EquipmentPositionHistory equipmentPositionHistory) {
        equipment = equipmentPositionHistory.getEquipment();
        date = equipmentPositionHistory.getDate();
        lat = equipmentPositionHistory.getLat();
        lon = equipmentPositionHistory.getLon();
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }
}
