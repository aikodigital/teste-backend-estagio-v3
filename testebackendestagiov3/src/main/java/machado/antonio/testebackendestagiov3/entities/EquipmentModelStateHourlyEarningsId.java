package machado.antonio.testebackendestagiov3.entities;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Implements 2 {@code foreign keys} (from {@link EquipmentModel} entity and
 * from {@link EquipmentState} entity) as primary key for
 * {@link EquipmentModelStateHourlyEarnings} entity.
 */
@Component
@Embeddable
public class EquipmentModelStateHourlyEarningsId {

	@Column(name = "equipment_model_id")
	private UUID equipmentModelId;

	@Column(name = "equipment_state_id")
	private UUID equipmentStateId;

	// Getters and Setters:

	public UUID getEquipmentModelId() {
		return equipmentModelId;
	}

	public void setEquipmentModelId(UUID equipmentModelId) {
		this.equipmentModelId = equipmentModelId;
	}

	public UUID getEquipmentStateId() {
		return equipmentStateId;
	}

	public void setEquipmentStateId(UUID equipmentStateId) {
		this.equipmentStateId = equipmentStateId;
	}

	@Override
	public String toString() {
		return "[equipmentModelId=" + equipmentModelId + ", equipmentStateId="
				+ equipmentStateId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(equipmentModelId, equipmentStateId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipmentModelStateHourlyEarningsId other = (EquipmentModelStateHourlyEarningsId) obj;
		return Objects.equals(equipmentModelId, other.equipmentModelId)
				&& Objects.equals(equipmentStateId, other.equipmentStateId);
	}

}
