package br.com.aikodigital.api.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Setter;
import lombok.Getter;


@Embeddable
@Getter
@Setter
public class Equipment_State_History_ID implements Serializable{
    
    @Column(name = "equipment_id")
    private UUID equipment_id;

    @Column(name = "equipment_state_id")
    private UUID equipment_state_id; 
}
