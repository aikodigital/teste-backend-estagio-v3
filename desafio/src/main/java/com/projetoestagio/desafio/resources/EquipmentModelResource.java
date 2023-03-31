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

import com.projetoestagio.desafio.entities.EquipmentModel;
import com.projetoestagio.desafio.services.EquipmentModelService;

@RestController
@RequestMapping(value = "/equipment_models")
public class EquipmentModelResource {
	
	@Autowired
	private EquipmentModelService service;
	
	@GetMapping
	public ResponseEntity<List<EquipmentModel>> findAll(){
		List<EquipmentModel> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentModel> findById(@PathVariable Long id){
		EquipmentModel obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<EquipmentModel> insert(@RequestBody EquipmentModel obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EquipmentModel> update(@PathVariable Long id, @RequestBody EquipmentModel obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
