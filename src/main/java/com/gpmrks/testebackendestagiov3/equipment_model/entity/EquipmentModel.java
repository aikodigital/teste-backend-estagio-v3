package com.gpmrks.testebackendestagiov3.equipment_model.entity;

import com.gpmrks.testebackendestagiov3.equipment_model.dto.EquipmentModelDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "equipment_model")
public class EquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "name")
    private String name;

    public EquipmentModel() {
    }

    public EquipmentModel(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
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

    public EquipmentModelDTO equipmentModelToDTO() {
        return new EquipmentModelDTO(id, name);
    }

    @Override
    public String toString() {
        return "EquipmentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
