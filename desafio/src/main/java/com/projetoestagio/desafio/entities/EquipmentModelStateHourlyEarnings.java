package com.projetoestagio.desafio.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_equipment_model_state_hourly_earnings")
public class EquipmentModelStateHourlyEarnings implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int value;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_equipment_model_id")
	private EquipmentModel equipmentModel;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_equipment_state_id")
	private EquipmentState equipmentState;
	


	public EquipmentModelStateHourlyEarnings(EquipmentModel equipmentModel, EquipmentState equipmentState, int value) {
		super();
		this.value = value;
	}
	
	public EquipmentModelStateHourlyEarnings() {
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public EquipmentModel getEquipmentModel() {
		return equipmentModel;
	}

	public EquipmentState getEquipmentState() {
		return equipmentState;
	}
	
	
	
}
