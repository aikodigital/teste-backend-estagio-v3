package machado.antonio.testebackendestagiov3.dtos;

import org.springframework.stereotype.Component;

import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarningsId;

@Component
public class EquipmentModelStateHourlyEarningsDTO {

	private EquipmentModelStateHourlyEarningsId id;
	
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
