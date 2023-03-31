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

import com.projetoestagio.desafio.entities.EquipmentStateHistory;
import com.projetoestagio.desafio.services.EquipmentStateHistoryService;

@RestController
@RequestMapping(value = "/equipment_state_history")
public class EquipmentStateHistoryResource {
	
	@Autowired
	private EquipmentStateHistoryService service;
	
	@GetMapping
	public ResponseEntity<List<EquipmentStateHistory>> findAll(){
		List<EquipmentStateHistory> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentStateHistory> findById(@PathVariable Long id){
		EquipmentStateHistory obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<EquipmentStateHistory> insert(@RequestBody EquipmentStateHistory obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EquipmentStateHistory> update(@PathVariable Long id, @RequestBody EquipmentStateHistory obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
