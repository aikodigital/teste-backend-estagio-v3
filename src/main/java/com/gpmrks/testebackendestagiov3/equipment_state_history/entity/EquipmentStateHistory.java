package com.gpmrks.testebackendestagiov3.equipment_state_history.entity;

import com.gpmrks.testebackendestagiov3.equipment.entity.Equipment;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state_history.dto.EquipmentStateHistoryDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    private LocalDateTime date;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_state_id")
    private EquipmentState equipmentState;

    public EquipmentStateHistory() {
        this.date = LocalDateTime.now();
    }

    public EquipmentStateHistory(Equipment equipment, LocalDateTime date, EquipmentState equipmentState) {
        this.equipment = equipment;
        this.date = LocalDateTime.now();
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
