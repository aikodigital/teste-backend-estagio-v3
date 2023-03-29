package com.projetoestagio.desafio.entities;

import java.io.Serializable;
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
@Table(name = "tb_equipment_state_history")
public class EquipmentStateHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_equipment_id")
	private Equipment equipmentId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_equipment_state_id")
	private EquipmentState equipmentStateId;
	
	private Instant date;

	public EquipmentStateHistory() {
		
	}
	
	public EquipmentStateHistory(Instant date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Equipment getEquipmentId() {
		return equipmentId;
	}

	public EquipmentState getEquipmentStateId() {
		return equipmentStateId;
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
		EquipmentStateHistory other = (EquipmentStateHistory) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
