package com.aikodigital.teste_estagio_backend_v3_Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Equipment extends Equipment_model {

    @ManyToOne
    @JoinColumn(name = "equipment_model_id")
    private Equipment_model equipment_model;

    public Equipment() {}

    public Equipment(String name, Equipment_model equipment_model) {
        super(name);
        this.equipment_model = equipment_model;
    }

    public Equipment_model getEquipment_model() {
        return equipment_model;
    }

    public void setEquipment_model(Equipment_model equipment_model) {
        this.equipment_model = equipment_model;
    }
}

/*
Essa classe recebe Annotation @Entity para indicar que representa uma entidade de banco de dados.
Ele estende a classe base Equipment_Model e adiciona um campo equipment_model com um relacionamento @ManyToOne à classe Equipment_model, que representa um relacionamento de chave estrangeira no banco de dados.

A Annotation @JoinColumn especifica o nome da coluna de chave estrangeira na tabela Equipment, que faz referência à coluna id na tabela Equipment_model.

Essa classe tem um construtor padrão e um construtor que usa os campos name e equipment_model como parâmetros. 
Ele também possui um método getter e setter para o campo equipment_model.
*/