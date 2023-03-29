package com.aiko.martins.matheus.equipments.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "data", schema = "operation")
public class Equipment_model {
    private Integer id;
    private String name;
    public Equipment_model(){

    }
    public Equipment_model(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId(){return id;}
    public void setId(Integer id){this.id =id;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipment_model other = (Equipment_model) obj;
        return Objects.equals(id, other.id);
    }
}
