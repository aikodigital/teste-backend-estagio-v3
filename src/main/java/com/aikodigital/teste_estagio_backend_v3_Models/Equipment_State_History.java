package com.aikodigital.teste_estagio_backend_v3_Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment_state_history")
public class Equipment_State_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "equipment_state_id", nullable = false)
    private Equipment_State equipment_state;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public Equipment_State_History() {
    }

    public Equipment_State_History(Equipment equipment, Equipment_State equipment_state, Date timestamp) {
        this.equipment = equipment;
        this.equipment_state = equipment_state;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Equipment_State getEquipment_state() {
        return equipment_state;
    }

    public void setEquipment_state(Equipment_State equipment_state) {
        this.equipment_state = equipment_state;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}


/*
Essa classe recebe a Annotation @Entity para indicar que representa uma entidade de banco de dados.
Possui dois campos de chave estrangeira: equipment e equipment_state, que possuem relacionamentos @ManyToOne com as classes Equipment e Equipment_State, respectivamente.

As anotações @JoinColumn especificam os nomes das colunas de chave estrangeira na tabela Equipment_State_History que fazem referência às colunas id nas tabelas Equipment e Equipment_State.

Esta classe também possui um campo de data do tipo LocalDateTime, que representa a data e hora da mudança de estado do equipamento. Ele tem um construtor padrão e um construtor que usa os campos equipment, equipment_state e date como parâmetros, bem como métodos getter e setter para cada campo.
*/

