package com.aikodigital.teste_estagio_backend_v3_Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Equipment_model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Equipment_model() {}

    public Equipment_model(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
