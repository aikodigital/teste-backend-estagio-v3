package com.aikodigital.teste_estagio_backend_v3_Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
public class Equipment_Model_State_Hourly_Earnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_model_id")
    @JsonIgnore
    private Equipment_Model equipmentModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_state_id")
    @JsonIgnore
    private Equipment_State equipmentState;

    @Temporal(TemporalType.TIMESTAMP)
    private Date hourly;

    private Double hourly_earnings;

    public Equipment_Model_State_Hourly_Earnings() {
    }

    public Equipment_Model_State_Hourly_Earnings(Equipment_Model equipmentModel, Equipment_State equipmentState, Date hourly, Double hourly_earnings) {
        this.equipmentModel = equipmentModel;
        this.equipmentState = equipmentState;
        this.hourly = hourly;
        this.hourly_earnings = hourly_earnings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipment_Model getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(Equipment_Model equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public Equipment_State getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(Equipment_State equipmentState) {
        this.equipmentState = equipmentState;
    }

    public Date getHourly() {
        return hourly;
    }

    public void setHourly(Date hourly) {
        this.hourly = hourly;
    }

    public Double getHourly_earnings() {
        return hourly_earnings;
    }

    public void setHourly_earnings(Double hourly_earnings) {
        this.hourly_earnings = hourly_earnings;
    }
}



/*
Essa classe recebe a Annotation @Entity para indicar que representa uma entidade de banco de dados.
Ele possui dois campos de chave estrangeira - equipmentModel e equipmentState - que possuem um relacionamento @ManyToOne com as classes Equipment_Model e Equipment_State, respectivamente.

Possui também um campo value do tipo BigDecimal, que representa o ganho por hora para o modelo do equipamento em um determinado estado.

Essa classe também possui um construtor padrão e um construtor que usa os campos equipmentModel, equipmentState e value como parâmetros, bem como métodos getter e setter para cada campo.
*/
