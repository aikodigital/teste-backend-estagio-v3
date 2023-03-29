package com.aikodigital.teste_estagio_backend_v3_Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Equipment_state_history {

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "equipment_state_id")
    private Equipment_state equipment_state;

    private LocalDateTime date;

    public Equipment_state_history() {}

    public Equipment_state_history(Equipment equipment, Equipment_state equipment_state, LocalDateTime date) {
        this.equipment = equipment;
        this.equipment_state = equipment_state;
        this.date = date;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Equipment_state getEquipment_state() {
        return equipment_state;
    }

    public void setEquipment_state(Equipment_state equipment_state) {
        this.equipment_state = equipment_state;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

/*
Essa classe recebe a Annotation @Entity para indicar que representa uma entidade de banco de dados.
Possui dois campos de chave estrangeira: equipment e equipment_state, que possuem relacionamentos @ManyToOne com as classes Equipment e Equipment_State, respectivamente.

As anotações @JoinColumn especificam os nomes das colunas de chave estrangeira na tabela Equipment_State_History que fazem referência às colunas id nas tabelas Equipment e Equipment_State.

Esta classe também possui um campo de data do tipo LocalDateTime, que representa a data e hora da mudança de estado do equipamento. Ele tem um construtor padrão e um construtor que usa os campos equipment, equipment_state e date como parâmetros, bem como métodos getter e setter para cada campo.
*/

