package com.projetoestagio.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoestagio.desafio.entities.Equipment;
import com.projetoestagio.desafio.entities.EquipmentState;
import com.projetoestagio.desafio.repositories.EquipmentStateRepository;
import com.projetoestagio.desafio.services.exceptions.DatabaseException;
import com.projetoestagio.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentStateService {
	@Autowired
	private EquipmentStateRepository repository;
	
	public List<EquipmentState> findAll(){
		return repository.findAll();
	}
	
	public EquipmentState findById(Long id) {
		Optional<EquipmentState> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public EquipmentState insert(EquipmentState obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	public EquipmentState update(Long id, EquipmentState obj) {
		try {
			EquipmentState entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(EquipmentState entity, EquipmentState obj) {
		entity.setName(obj.getName());
		entity.setColor(obj.getColor());
		
	}
	
	
}
