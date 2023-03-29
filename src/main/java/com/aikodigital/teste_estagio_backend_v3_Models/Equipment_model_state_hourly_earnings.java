package com.aikodigital.teste_estagio_backend_v3_Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Equipment_model_state_hourly_earnings {
    
    @ManyToOne
    @JoinColumn(name = "equipment_model_id")
    private Equipment_model equipmentModel;
    
    @ManyToOne
    @JoinColumn(name = "equipment_state_id")
    private Equipment_state equipmentState;
    
    private BigDecimal value;

    public Equipment_model_state_hourly_earnings() {}

    public Equipment_model_state_hourly_earnings(Equipment_model equipmentModel, Equipment_state equipmentState, BigDecimal value) {
        this.equipmentModel = equipmentModel;
        this.equipmentState = equipmentState;
        this.value = value;
    }

    public Equipment_model getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(Equipment_model equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public Equipment_state getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(Equipment_state equipmentState) {
        this.equipmentState = equipmentState;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

/*
Essa classe recebe a Annotation @Entity para indicar que representa uma entidade de banco de dados.
Ele possui dois campos de chave estrangeira - equipmentModel e equipmentState - que possuem um relacionamento @ManyToOne com as classes Equipment_Model e Equipment_State, respectivamente.

Possui também um campo value do tipo BigDecimal, que representa o ganho por hora para o modelo do equipamento em um determinado estado.

Essa classe também possui um construtor padrão e um construtor que usa os campos equipmentModel, equipmentState e value como parâmetros, bem como métodos getter e setter para cada campo.
*/
