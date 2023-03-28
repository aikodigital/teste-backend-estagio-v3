package br.com.projeto.api.model;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment_state")
public class EquipamentoState {
    
    @Id
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "equipment_state_id")
    private EquipamentoStateHistory equipamentoStateHistory;
    
    private String name;

    private String color;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
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
}
