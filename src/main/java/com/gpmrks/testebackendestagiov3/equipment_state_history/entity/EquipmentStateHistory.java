package com.gpmrks.testebackendestagiov3.equipment_state_history.entity;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_position_history.dto.EquipmentPositionHistoryDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "equipment_state_history")
@IdClass(EquipmentStateHistoryId.class)
public class EquipmentStateHistory {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Id
    @Column(name = "date")
    private LocalDate date;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_state_id")
    private EquipmentState equipmentState;

    public EquipmentStateHistory() {
    }

    public EquipmentStateHistory(Equipment equipment, LocalDate date, EquipmentState equipmentState) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    public EquipmentStateHistoryDTO equipmentStateHistoryDTO() {
        return new EquipmentStateHistoryDTO(equipment, date, equipmentState);
    }

    @Override
    public String toString() {
        return "EquipmentStateHistory{" +
                "equipment=" + equipment +
                ", date=" + date +
                ", equipmentState=" + equipmentState +
                '}';
    }
}
