package com.api.aikobackendteste.primaryKeys;

import com.api.aikobackendteste.models.EquipmentModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Date;

public class EquipmentStateHistoryPk implements Serializable {
    @OneToOne
    @JoinColumn(name="equipment_id", referencedColumnName = "id")
    private EquipmentModel equipmentModel;
    @Column(nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public EquipmentStateHistoryPk() {
    }

    public EquipmentStateHistoryPk(EquipmentModel equipmentModel, Date date) {
        this.equipmentModel = equipmentModel;
        this.date = date;
    }
}
