package com.api.aikobackendteste.models;

import com.api.aikobackendteste.primaryKeys.EquipmentPositionHistoryPk;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "equipment_position_history")
public class EquipmentPositionHistoryModel implements Serializable {
    @EmbeddedId
    private EquipmentPositionHistoryPk id;
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    @DecimalMax("90.0")
    @DecimalMin("-90.0")
    private double lat;
    @Column(nullable = false)
    @DecimalMax("90.0")
    @DecimalMin("-90.0")
    private double lon;

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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
