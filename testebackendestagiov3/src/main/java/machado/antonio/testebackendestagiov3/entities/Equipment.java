package machado.antonio.testebackendestagiov3.entities;

import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "equipment", schema = "operation")
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "equipment_model_id")
	private UUID equipmentModelId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipment_model_id", referencedColumnName = "id", insertable = false, updatable = false)
	private EquipmentModel equipmentModel;

	@Column(nullable = false)
	private String name;

	// Getters and Setters methods:

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getEquipmentModelId() {
		return equipmentModelId;
	}

	public void setEquipmentModelId(UUID equipmentModelId) {
		this.equipmentModelId = equipmentModelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
