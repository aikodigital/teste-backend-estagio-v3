package com.api.aikobackendteste.models;

import com.api.aikobackendteste.primaryKeys.EquipmentStateHistoryPk;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "equipment_state_history")
public class EquipmentStateHistoryModel implements Serializable {
    @EmbeddedId
    private EquipmentStateHistoryPk id;
    private static final long serialVersionUID = 1L;

    public EquipmentModel getEquipmentModel() {
        return this.id.getEquipmentModel();
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.id.setEquipmentModel(equipmentModel);
    }

    public Date getDate() {
        return this.id.getDate();
    }

    public void setDate(Date date) {
        this.id.setDate(date);
    }



}
