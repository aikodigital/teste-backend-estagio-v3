package br.com.projeto.api.model;
import java.util.UUID;
import jakarta.persistence.*;
@Entity
@Table(name = "equipment_state_history")
public class EquipamentoStateHistory 
{
    @Id
    private UUID equipment_id;
    private String date;
    private UUID equipment_state_id;
    
    public UUID getEquipment_id() {
        return equipment_id;
    }
    public void setEquipment_id(UUID equipment_id) {
        this.equipment_id = equipment_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public UUID getEquipment_state_id() {
        return equipment_state_id;
    }
    public void setEquipment_state_id(UUID equipment_state_id) {
        this.equipment_state_id = equipment_state_id;
    }
}