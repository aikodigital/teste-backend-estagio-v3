package com.projetoestagio.desafio.entities;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_equipment_position_history")
public class EquipmentPositionHistory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Instant date;
	
	private Long lat;
	private Long lon;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_equipment_id")
	private Equipment equipment;
	
	public EquipmentPositionHistory() {
		
	}

	public EquipmentPositionHistory(Instant date, Long lat, Long lon, Equipment equipment) {
		super();
		this.date = date;
		this.lat = lat;
		this.lon = lon;
		this.equipment = equipment;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Long getLat() {
		return lat;
	}

	public void setLat(Long lat) {
		this.lat = lat;
	}

	public Long getLon() {
		return lon;
	}

	public void setLon(Long lon) {
		this.lon = lon;
	}

	public Long getId() {
		return id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipmentPositionHistory other = (EquipmentPositionHistory) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
