package machado.antonio.testebackendestagiov3.entities;

import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Implements 2 {@code foreign keys} (from {@link Equipment} entity and from
 * {@link EquipmentState} entity) as primary key for
 * {@link EquipmentStateHistory} entity.
 */
@Component
@Embeddable
public class EquipmentStateHistoryId {

	@Column(name = "equipment_id")
	private UUID equipmentId;

	@Column(name = "equipment_state_id")
	private UUID equipmentStateId;

	// Getters and Setters:

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public UUID getEquipmentStateId() {
		return equipmentStateId;
	}

	public void setEquipmentStateId(UUID equipmentStateId) {
		this.equipmentStateId = equipmentStateId;
	}

}
