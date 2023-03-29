package com.api.aikobackendteste.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="equipment")
public class EquipmentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "equipment_model_id", referencedColumnName = "id")
    private EquipmentModelModel equipmentModelModel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EquipmentModelModel getEquipmentModelModel() {
        return equipmentModelModel;
    }

    public void setEquipmentModelModel(EquipmentModelModel equipmentModelModel) {
        this.equipmentModelModel = equipmentModelModel;
    }
}
