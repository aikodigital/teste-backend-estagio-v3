package com.aiko.martins.matheus.equipments.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "data", schema = "operation")
public class Equipment_state {
    private Integer id;
    private String name;
    private String color;

    public Equipment_state() {
    }

    public Equipment_state(Integer id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        Equipment_state other = (Equipment_state) obj;
        return Objects.equals(id, other.id);
    }
}
