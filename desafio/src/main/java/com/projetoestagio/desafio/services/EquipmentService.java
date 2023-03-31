package com.projetoestagio.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoestagio.desafio.entities.Equipment;
import com.projetoestagio.desafio.repositories.EquipmentRepository;
import com.projetoestagio.desafio.services.exceptions.DatabaseException;
import com.projetoestagio.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentService {
	@Autowired
	private EquipmentRepository repository;
	
	public List<Equipment> findAll(){
		return repository.findAll();
	}
	
	public Equipment findById(Long id) {
		Optional<Equipment> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public Equipment insert(Equipment obj) {
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
	
	public Equipment update(Long id, Equipment obj) {
		try {
			Equipment entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
		
	}

	private void updateData(Equipment entity, Equipment obj) {
		entity.setName(obj.getName());
		entity.setEquipmentModelId(obj.getEquipmentModelId());
		
	}
	
	
}
