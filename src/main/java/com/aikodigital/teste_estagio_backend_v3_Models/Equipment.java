package com.aikodigital.teste_estagio_backend_v3_Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "equipment_model_id")
    private Equipment_Model equipmentModel;

    public Equipment() {}

    public Equipment(String name, Equipment_Model equipmentModel) {
        this.name = name;
        this.equipmentModel = equipmentModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Equipment_Model getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(Equipment_Model equipmentModel) {
        this.equipmentModel = equipmentModel;
    }
}


/*
Essa classe recebe Annotation @Entity para indicar que representa uma entidade de banco de dados.
Ele estende a classe base Equipment_Model e adiciona um campo equipment_model com um relacionamento @ManyToOne à classe Equipment_model, que representa um relacionamento de chave estrangeira no banco de dados.

A Annotation @JoinColumn especifica o nome da coluna de chave estrangeira na tabela Equipment, que faz referência à coluna id na tabela Equipment_model.

Essa classe tem um construtor padrão e um construtor que usa os campos name e equipment_model como parâmetros. 
Ele também possui um método getter e setter para o campo equipment_model.
*/