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
@Table(name = "equipment_position_history")
public class Equipment_Position_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public Equipment_Position_History() {
    }

    public Equipment_Position_History(Equipment equipment, double latitude, double longitude, Date timestamp) {
        this.equipment = equipment;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}


/*
Essa classe recebe Annotation @Entity para indicar que representa uma entidade de banco de dados.
Ele possui um campo de chave estrangeira chamado equipment que possui um relacionamento @ManyToOne com a classe Equipment.

Possui também um campo de data do tipo LocalDateTime, que representa a data e hora da atualização da posição do equipamento.
Possui dois campos de string chamados lat e lon, que representam a latitude e a longitude da atualização da posição do equipamento.

Essa classe também possui um construtor padrão e um construtor que usa os campos equipment, date, lat e lon como parâmetros, bem como métodos getter e setter para cada campo.
*/