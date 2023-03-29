package machado.antonio.testebackendestagiov3.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "equipment_model_state_hourly_earnings", schema = "operation")
public class EquipmentModelStateHourlyEarnings {

	@EmbeddedId
	private EquipmentModelStateHourlyEarningsId id;

	@ManyToOne
	@JoinColumn(name = "equipment_model_id", insertable = false, updatable = false)
	private EquipmentModel equipmentModel;

	@ManyToOne
	@JoinColumn(name = "equipment_state_id", insertable = false, updatable = false)
	private EquipmentState equipmentState;

	@Column(nullable = false)
	private Double value;

	// Getters and Setters:

	public EquipmentModelStateHourlyEarningsId getId() {
		return id;
	}

	public void setId(EquipmentModelStateHourlyEarningsId id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
