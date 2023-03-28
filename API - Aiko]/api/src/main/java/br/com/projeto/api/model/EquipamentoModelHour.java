package br.com.projeto.api.model;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
public class EquipamentoModelHour 
{
    @Id
    private UUID equipment_model_id;
    
    private UUID equipment_state_id;

    private double value;

    public UUID getEquipment_model_id() {
        return equipment_model_id;
    }
    public void setEquipment_model_id(UUID equipment_model_id) {
        this.equipment_model_id = equipment_model_id;
    }
    public UUID getEquipment_state_id() {
        return equipment_state_id;
    }
    public void setEquipment_state_id(UUID equipment_state_id) {
        this.equipment_state_id = equipment_state_id;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
}
