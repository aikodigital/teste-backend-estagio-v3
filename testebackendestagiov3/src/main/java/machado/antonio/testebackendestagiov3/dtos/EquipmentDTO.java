package machado.antonio.testebackendestagiov3.dtos;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EquipmentDTO {

	private UUID equipmentModelId;

	private String name;

	// Getters and Setters:

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
