package com.gpmrks.testebackendestagiov3.equipment_state.entity;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "equipment_state")
public class EquipmentState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    public EquipmentState() {
    }

    public EquipmentState(UUID id, String name, String color) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public EquipmentStateDTO equipmentStateToDTO() {
        return new EquipmentStateDTO(id, name, color);
    }

    @Override
    public String toString() {
        return "EquipmentState{" +
                "id=" + id +
                ", name=" + name +
                ", color='" + color + '\'' +
                '}';
    }
}
