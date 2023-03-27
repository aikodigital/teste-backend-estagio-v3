package br.com.aikodigital.api.models;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipment_position_history")
@Getter
@Setter
public class Equipment_position_history {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "equipment_id", referencedColumnName ="id")
    private UUID equipment_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;
}
