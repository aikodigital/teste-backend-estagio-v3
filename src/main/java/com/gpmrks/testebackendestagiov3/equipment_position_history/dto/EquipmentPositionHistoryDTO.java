package com.gpmrks.testebackendestagiov3.equipment_position_history.dto;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;

import java.time.LocalDate;
public class EquipmentPositionHistoryDTO {

    private Equipment equipment;
    private LocalDate date;
    private Float lat;
    private Float lon;

    public EquipmentPositionHistoryDTO(Equipment equipment, LocalDate date, Float lat, Float lon) {
        this.equipment = equipment;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public LocalDate getDate() {
        return date;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }
}
