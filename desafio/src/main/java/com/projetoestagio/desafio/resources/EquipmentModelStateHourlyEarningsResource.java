package com.projetoestagio.desafio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoestagio.desafio.entities.EquipmentModelStateHourlyEarnings;
import com.projetoestagio.desafio.services.EquipmentModelStateHourlyEarningsService;

@RestController
@RequestMapping(value = "/equipment_model_state_hourly_earnings")
public class EquipmentModelStateHourlyEarningsResource {
	
	@Autowired
	private EquipmentModelStateHourlyEarningsService service;
	
	@GetMapping
	public ResponseEntity<List<EquipmentModelStateHourlyEarnings>> findAll(){
		List<EquipmentModelStateHourlyEarnings> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentModelStateHourlyEarnings> findById(@PathVariable Long id){
		EquipmentModelStateHourlyEarnings obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<EquipmentModelStateHourlyEarnings> insert(@RequestBody EquipmentModelStateHourlyEarnings obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EquipmentModelStateHourlyEarnings> update(@PathVariable Long id, @RequestBody EquipmentModelStateHourlyEarnings obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
