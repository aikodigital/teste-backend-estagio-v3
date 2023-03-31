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

import com.projetoestagio.desafio.entities.EquipmentState;
import com.projetoestagio.desafio.services.EquipmentStateService;

@RestController
@RequestMapping(value = "/equipment_states")
public class EquipmentStateResource {
	
	@Autowired
	private EquipmentStateService service;
	
	@GetMapping
	public ResponseEntity<List<EquipmentState>> findAll(){
		List<EquipmentState> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentState> findById(@PathVariable Long id){
		EquipmentState obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<EquipmentState> insert(@RequestBody EquipmentState obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EquipmentState> update(@PathVariable Long id, @RequestBody EquipmentState obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
