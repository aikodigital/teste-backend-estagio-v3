package com.aiko.martins.matheus.equipments.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "data", schema = "operation")
public class Equipment_position_history {
    private Integer equipment_id;
    private Date date;
    private float lat;
    private float lon;

    public Equipment_position_history() {
    }

    public Equipment_position_history(Integer equipment_id, Date date, float lat, float lon) {
        this.equipment_id = equipment_id;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getEquipment_id() {
        return equipment_id;
    }
    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipment_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipment_position_history other = (Equipment_position_history) obj;
        return Objects.equals(equipment_id, other.equipment_id);
    }
}
