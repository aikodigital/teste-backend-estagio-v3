package com.aiko.martins.matheus.equipments.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "data", schema = "operation")
public class Equipment_model_state_hourly_earnings {
    private Integer equipment_model_id;
    private Integer equipment_state_id;
    private Float value;
    public Equipment_model_state_hourly_earnings(){
    }
    public Equipment_model_state_hourly_earnings(Integer equipment_model_id, Integer equipment_state_id, Float value) {
        super();
        this.equipment_model_id = equipment_model_id;
        this.equipment_state_id = equipment_state_id;
        this.value = value;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getEquipment_model_id(){return equipment_model_id;}
    public void setEquipment_model_id(Integer equipment_model_id) {
        this.equipment_model_id = equipment_model_id;
    }

    public Integer getEquipment_state_id() {
        return equipment_state_id;
    }

    public void setEquipment_state_id(Integer equipment_state_id) {
        this.equipment_state_id = equipment_state_id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipment_model_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipment_model_state_hourly_earnings other = (Equipment_model_state_hourly_earnings) obj;
        return Objects.equals(equipment_model_id, other.equipment_model_id);
    }

}
