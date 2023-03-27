package br.com.aikodigital.api.models;



import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
@Getter
@Setter
public class Equipment_MSHE {
    
    @EmbeddedId
    private Equipment_MSHE_ID id;

    @Column(name="value")
    private double value;

}
