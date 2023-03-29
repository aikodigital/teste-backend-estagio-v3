package machado.antonio.testebackendestagiov3.dtos;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EquipmentPositionHistoryDTO {

	private UUID equipmentId;

	private Double lat;

	private Double lon;

	// Getters and Setters:
	
	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

}
