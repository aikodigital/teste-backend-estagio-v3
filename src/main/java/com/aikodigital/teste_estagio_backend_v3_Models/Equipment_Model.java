package com.aikodigital.teste_estagio_backend_v3_Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "equipment_model")
public class Equipment_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    public Equipment_Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Equipment_Model() {}

    public Equipment_Model(String name) {
        this.name = name;
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
}


/*
Essa classe recebe a Annotation @MappedSuperclass para indicar que é uma classe base que não será mapeada para o banco de dados.
Ele contém os mesmos campos e métodos do exemplo anterior, mas com a adição da palavra-chave abstract na definição da classe.

Essa classe base pode ser estendida por subclasses
que representam tipos específicos de equipamentos com campos e métodos adicionais.

Essas subclasses também recebem Annotation @Entity para indicar que representam entidades de banco de dados.
*/
