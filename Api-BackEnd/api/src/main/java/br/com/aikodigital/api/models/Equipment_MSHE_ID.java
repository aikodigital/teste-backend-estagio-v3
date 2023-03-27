package br.com.aikodigital.api.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Equipment_MSHE_ID {
    
    @Column(name="equipment_model_id")
    private UUID equipment_model_id;

    @Column(name = "equipment_state_id")
    private UUID equipment_state_id;
}


