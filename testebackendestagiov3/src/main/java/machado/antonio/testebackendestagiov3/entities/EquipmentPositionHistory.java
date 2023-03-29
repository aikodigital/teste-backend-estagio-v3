package machado.antonio.testebackendestagiov3.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "equipment_position_history", schema = "operation")
public class EquipmentPositionHistory {

	/**
	 * Shared primary key from {@link Equipment} entity.
	 */
	@Id
	@Column(name = "equipment_id")
	private UUID equipmentId;

	/**
	 * Designating a <code>ManyToOne</code> relationship attribute that provides the
	 * mapping for the primary key from {@link Equipment} entity.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("id")
	@JoinColumn(name = "equipment_id", insertable = false, updatable = false)
	private Equipment equipment;

	@Column(nullable = false)
	private LocalDateTime date;

	/**
	 * Latitude in WGS84
	 */
	@Column
	private Double lat;

	/**
	 * Longitude in WGS84
	 */
	@Column
	private Double lon;

	// Getters and Setters:

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * 
	 * @return lat the latitude in WGS84
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * 
	 * @param lat the latitude in WGS84
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * 
	 * @return lon the longitude in WGS84
	 */
	public Double getLon() {
		return lon;
	}

	/**
	 * 
	 * @param lon the longitude in WGS84
	 */
	public void setLon(Double lon) {
		this.lon = lon;
	}

}
