package com.aikodigital.teste_estagio_backend_v3_Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment_state")
public class Equipment_State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String color;

    public Equipment_State() {}

    public Equipment_State(String name, String color) {
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}


/*
Essa classe recebe a Annotation @MappedSuperclass para indicar que é uma classe base que não será mapeada para o banco de dados.

Ele contém três campos: id, nome e cor, e os métodos getter e setter correspondentes para cada campo.

Essa classe base pode ser estendida por subclasses que representam tipos específicos de estados de equipamentos com campos e métodos adicionais.
Essas subclasses também são anotadas com @Entity para indicar que representam entidades de banco de dados.
*/