package machado.antonio.testebackendestagiov3.dtos;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EquipmentStateHistoryDTO {

	private UUID equipmentId;

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
