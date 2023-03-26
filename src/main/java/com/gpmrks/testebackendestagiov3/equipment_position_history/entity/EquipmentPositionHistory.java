package com.gpmrks.testebackendestagiov3.equipment_position_history.entity;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "equipment_position_history")
@IdClass(EquipmentPositionHistoryId.class)
public class EquipmentPositionHistory {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;

    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lon")
    private Float lon;

    public EquipmentPositionHistory() {
    }

    public EquipmentPositionHistory(Equipment equipment, LocalDate date, Float lat, Float lon) {
        this.equipment = equipment;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public EquipmentPositionHistoryDTO equipmentPositionHistoryToDTO() {
        return new EquipmentPositionHistoryDTO(equipment, date, lat, lon);
    }

    @Override
    public String toString() {
        return "EquipmentPositionHistory{" +
                "equipment=" + equipment +
                ", date=" + date +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
