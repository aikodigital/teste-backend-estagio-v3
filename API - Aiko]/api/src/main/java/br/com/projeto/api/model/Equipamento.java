package br.com.projeto.api.model;
import java.util.UUID;
import jakarta.persistence.*;
@Entity
@Table(name = "equipment")
public class Equipamento {

    @Id
    @Column(name = "id")
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id")
    private EquipamentoPositionHistory equipamentoPositionHistory;

    private String name;
    
    private UUID equipment_model_id;

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
    public UUID getEquipment_model_id() {
        return equipment_model_id;
    }
    public void setEquipment_model_id(UUID equipment_model_id) {
        this.equipment_model_id = equipment_model_id;
    }
}
